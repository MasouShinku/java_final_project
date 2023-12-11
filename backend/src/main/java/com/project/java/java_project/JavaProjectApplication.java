package com.project.java.java_project;

import com.project.java.java_project.repository.CommentRepository;
import com.project.java.java_project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaProjectApplication.class, args);
    }

}
