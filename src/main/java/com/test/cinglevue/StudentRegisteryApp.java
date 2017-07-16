package com.test.cinglevue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
@ComponentScan
@SpringBootApplication(scanBasePackages = {"com.test.cinglevue"})

public class StudentRegisteryApp {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public static void main(String[] args) {
        SpringApplication.run(StudentRegisteryApp.class, args);
    }
}
