'use strict';

angular.module('stuApp').factory('StudentService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllStudent: loadAllStudent,
                getAllStudent:getAllStudent,
                getStudent: getStudent,
                createStudent: createStudent,
                updateStudent: updateStudent,
                removeStudent: removeStudent
            };

            return factory;

            function loadAllStudent() {
                console.log('Fetching all Student');
                var deferred = $q.defer();
                $http.get(urls.STUDENT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Student');
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Student');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllStudent(){
                return $localStorage.students;
            }

            function getStudent(id) {
                console.log('Fetching Student with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.STUDENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Student with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Student with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createStudent(student) {
                console.log('Creating Student');
                var deferred = $q.defer();
                $http.post(urls.STUDENT_SERVICE_API, user)
                    .then(
                        function (response) {
                            loadAllStudent();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating student : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateStudent(student, id) {
                console.log('Updating Student with id '+id);
                var deferred = $q.defer();
                $http.put(urls.STUDENT_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            loadAllStudent();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Student with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeStudent(id) {
                console.log('Removing Student with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.STUDENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllStudent();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing User with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);