package com.student.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.student.management.dto.AddStudentRequestDto;
import com.student.management.dto.StudentDto;

@Service
public interface StudentService {
	List<StudentDto> getAllStudents();

	StudentDto getStudentById(Long id);

	StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

	void deleteStudentById(Long id);

	StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

	StudentDto patchStudent(Long id, Map<String, Object> updates);
}
