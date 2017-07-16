package com.test.cinglevue.service;

import java.util.List;

import com.test.cinglevue.model.Student;

public interface StudentService {

    public Student findById(Integer id);

    public void saveStudent(Student student);

    public void updateStudent(Student student);

    public void deleteById(int id);

    public boolean isStudentExist(Student student);

    public List<Student> getAllStudent();

}