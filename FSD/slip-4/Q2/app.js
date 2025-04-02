var app = angular.module("studentApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/register", {
            templateUrl: "views/register.html",
            controller: "StudentController"
        })
        .otherwise({
            redirectTo: "/register"
        });
});

app.controller("StudentController", function($scope, $http) {
    $scope.student = {};
    $scope.firstNameMessage = "";
    $scope.lastNameMessage = "";
    $scope.ageMessage = "";
    $scope.greeting = "";

    // Fetch greeting based on time using AJAX
    $http.get("data/greetings.json").then(function(response) {
        var hour = new Date().getHours();
        if (hour < 12) {
            $scope.greeting = response.data.morning;
        } else if (hour < 18) {
            $scope.greeting = response.data.afternoon;
        } else {
            $scope.greeting = response.data.evening;
        }
    });

    // Validate first and last name (only alphabets)
    $scope.validateName = function(type) {
        var name = type === 'first' ? $scope.student.firstName : $scope.student.lastName;
        var regex = /^[A-Za-z]+$/;
        
        if (!name) {
            if (type === 'first') $scope.firstNameMessage = "First name is required";
            else $scope.lastNameMessage = "Last name is required";
        } else if (!regex.test(name)) {
            if (type === 'first') $scope.firstNameMessage = "First name must contain only letters";
            else $scope.lastNameMessage = "Last name must contain only letters";
        } else {
            if (type === 'first') $scope.firstNameMessage = "";
            else $scope.lastNameMessage = "";
        }
    };

    // Validate age (18 - 50)
    $scope.validateAge = function() {
        if (!$scope.student.age) {
            $scope.ageMessage = "Age is required";
        } else if ($scope.student.age < 18 || $scope.student.age > 50) {
            $scope.ageMessage = "Age must be between 18 and 50";
        } else {
            $scope.ageMessage = "";
        }
    };

    // Submit form
    $scope.submitForm = function() {
        $scope.validateName('first');
        $scope.validateName('last');
        $scope.validateAge();

        if (!$scope.firstNameMessage && !$scope.lastNameMessage && !$scope.ageMessage) {
            alert("Registration Successful!");
        } else {
            alert("Please correct the errors before submitting.");
        }
    };
});
