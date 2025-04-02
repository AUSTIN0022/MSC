var app = angular.module("validationApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/form", {
            templateUrl: "views/form.html",
            controller: "ValidationController"
        })
        .otherwise({
            redirectTo: "/"
        });
});

app.controller("ValidationController", function($scope) {
    $scope.user = {};
    $scope.usernameMessage = "";
    $scope.passwordMessage = "";

    $scope.validateUsername = function() {
        if (!$scope.user.username) {
            $scope.usernameMessage = "Enter username";
        } else if ($scope.user.username.length < 3) {
            $scope.usernameMessage = "Username is too short";
        } else {
            $scope.usernameMessage = "Valid username";
        }
    };

    $scope.validatePassword = function() {
        if (!$scope.user.password || $scope.user.password.length < 8) {
            $scope.passwordMessage = "Password must be at least 8 characters";
        } else {
            $scope.passwordMessage = "";
        }
    };

    $scope.submitForm = function() {
        $scope.validateUsername();
        $scope.validatePassword();

        if ($scope.usernameMessage === "Valid username" && !$scope.passwordMessage) {
            alert("Form submitted successfully!");
        } else {
            alert("Please correct the errors before submitting.");
        }
    };
});
