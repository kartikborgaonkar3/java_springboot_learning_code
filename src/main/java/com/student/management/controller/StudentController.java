package com.student.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.student.management.dto.StudentDto;
import com.student.management.entity.Student;
import com.student.management.repository.StudentRepository;
import com.student.management.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;
	
	
//	private final StudentRepository studentRepository;
	
//	public StudentController(StudentRepository studentRepository) {
//		this.studentRepository = studentRepository;
//	}


//	@GetMapping("/student")
//	public StudentDto getStudent() {
//		return new StudentDto(12L, "Ram", "ram@gmail.com");
//	}
	
//	@GetMapping("/student")
//	public List<Student> getStudent() {
//		return studentRepository.findAll();
//	}
	
	@GetMapping("/students")
	public List<StudentDto> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/students/name/{name}")
	public String getStudentByName(@PathVariable String name){
		return "I'm returning name==> " + name;
	}
	
	@GetMapping("/students/{id}")
	public StudentDto getStudentById(@PathVariable Long id){
		return studentService.getStudentById(id);
	}

}
