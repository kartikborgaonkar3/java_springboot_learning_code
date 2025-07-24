package com.student.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.student.management", "com.student.management.controller", "com.student.management.dto", "com.student.management.entity", "com.student.management.repository", "com.student.management.service", "com.student.management.service.implementation"}) //because it is out of main package; need to scan or else will give whiteLable error.   
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
