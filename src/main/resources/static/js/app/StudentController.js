'use strict';

angular.module('stuApp').controller('StudentController',
    ['StudentService', '$scope',  function( StudentService, $scope) {

        var self = this;
        self.student = {};
        self.students=[];

        self.submit = submit;
        self.getAllStudent = getAllStudent;
        self.createStudent = createStudent;
        self.updateStudent = updateStudent;
        self.removeStudent = removeStudent;
        self.editStudent = editStudent;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.student.id === undefined || self.student.id === null) {
                console.log('Saving New student', self.student);
                createStudent(self.student);
            } else {
                updateStudent(self.student, self.student.id);
                console.log('student updated with id ', self.student.id);
            }
        }

        function createStudent(student) {
            console.log('Createting student');
            StudentService.createStudent(student)
                .then(
                    function (response) {
                        console.log('student created successfully');
                        self.successMessage = 'student created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.student={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating student');
                        self.errorMessage = 'Error while creating student: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateStudent(student, id){
            console.log('About to update student');
            StudentService.updateStudent(student, id)
                .then(
                    function (response){
                        console.log('student successfully');
                        self.successMessage='student updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating student');
                        self.errorMessage='Error while updating student '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeStudent(id){
            console.log('About to remove student with id '+id);
            StudentService.removeUser(id)
                .then(
                    function(){
                        console.log('student '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing student '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllStudent(){
            return StudentService.getAllStudent();
        }

        function editStudent(id) {
            self.successMessage='';
            self.errorMessage='';
            StudentService.getStudent(id).then(
                function (student) {
                    self.student = student;
                },
                function (errResponse) {
                    console.error('Error while removing student ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.student={};
            $scope.myForm.$setPristine();
        }
    }


    ]);