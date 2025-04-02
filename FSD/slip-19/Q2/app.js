var app = angular.module("userApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/form", {
            templateUrl: "views/form.html",
            controller: "FormController"
        })
        .otherwise({
            redirectTo: "/"
        });
});

app.controller("FormController", function($scope) {
    $scope.user = {};

    $scope.submitForm = function() {
        if ($scope.userForm.$valid) {
            alert("Form submitted successfully!");
        } else {
            alert("Please correct the errors before submitting.");
        }
    };
});
