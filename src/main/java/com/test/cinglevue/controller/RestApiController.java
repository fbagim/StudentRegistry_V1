package com.test.cinglevue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.test.cinglevue.model.Student;
import com.test.cinglevue.service.StudentService;
import com.test.cinglevue.util.CustomErrorType;


@RestController
@RequestMapping("/StudentRegisteryApp/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    StudentService studentService;

    // -------------------Retrieve all students --------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudent() {
        List<Student> allStudent = studentService.getAllStudent();
        if (allStudent.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(allStudent, HttpStatus.OK);
    }

    // -------------------Get student -----------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
        logger.info("Fetching student with id {}", id);
        Student student = studentService.findById(id);
        if (student == null) {
            logger.error("student with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // -------------------Create student -------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        logger.info("Creating student : {}", student);

        if (studentService.isStudentExist(student)) {
            logger.error("Unable to create. A student with id {} already exist", student.getId());
            return new ResponseEntity(new CustomErrorType("Unable to create. A student with id " +
                    student.getId() + " already exist."), HttpStatus.CONFLICT);
        }
        studentService.saveStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a student ------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        logger.info("Updating Student with id {}", id);

        Student studentCurrent = studentService.findById(id);

        if (studentCurrent == null) {
            logger.error("Unable to update. Student with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Student with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studentCurrent.setFirstName(student.getFirstName());
        studentCurrent.setLastName(student.getLastName());
        studentCurrent.setAddress(student.getAddress());
        studentCurrent.setAge(student.getAge());
        studentCurrent.setTelephone(student.getTelephone());

        studentService.updateStudent(studentCurrent);
        return new ResponseEntity<Student>(studentCurrent, HttpStatus.OK);
    }

    // ------------------- Delete a student -----------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting student with id {}", id);

        Student student = studentService.findById(id);
        if (student == null) {
            logger.error("Unable to delete. student with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. student with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

}