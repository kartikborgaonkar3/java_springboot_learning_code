package com.student.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.management.dto.StudentDto;

@Service
public interface StudentService {
	List<StudentDto> getAllStudents();

	StudentDto getStudentById(Long id);
}
