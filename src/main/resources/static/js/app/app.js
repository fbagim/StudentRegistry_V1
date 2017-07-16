var app = angular.module('stuApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/StudentRegisteryApp',
    STUDENT_SERVICE_API : 'http://localhost:8080/StudentRegisteryApp/api/student/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'StudentController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q,StudentService) {
                        console.log('Load all student');
                        var deferred = $q.defer();
                        StudentService.loadAllStudent().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

