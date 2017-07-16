package com.test.cinglevue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.test.cinglevue.dao.StudentDao;
import com.test.cinglevue.model.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student findById(Integer id) {
        return studentDao.findOne(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.save(student);
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteById(int id) {
        studentDao.delete(id);
    }

    @Override
    public List<Student> getAllStudent() {
        Iterable<Student> tIterable = studentDao.findAll();
        return Lists.newArrayList(tIterable);
    }

    public boolean isStudentExist(Student student) {
        return findById(student.getId()) != null;
    }

}
