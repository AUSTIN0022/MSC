var app = angular.module("syllabusApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/daa", {
            templateUrl: "views/daa.html"
        })
        .when("/mad", {
            templateUrl: "views/mad.html"
        })
        .when("/spm", {
            templateUrl: "views/spm.html"
        })
        .when("/cloud", {
            templateUrl: "views/cloud.html"
        })
        .when("/fsd", {
            templateUrl: "views/fsd.html"
        })
        .otherwise({
            template: "<h3>Welcome to the M.Sc (CS) Semester-II Syllabus Portal. Click on a subject to view its syllabus.</h3>"
        });
});
