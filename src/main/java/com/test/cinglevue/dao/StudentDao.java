package com.test.cinglevue.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.test.cinglevue.model.Student;

@Component
public interface StudentDao extends ElasticsearchRepository<Student, Integer> {

}
