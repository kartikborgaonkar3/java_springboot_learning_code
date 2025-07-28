package com.student.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.management.dto.AddStudentRequestDto;
import com.student.management.dto.StudentDto;
import com.student.management.entity.Student;
import com.student.management.repository.StudentRepository;
import com.student.management.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
	private final StudentService studentService;

//	private final StudentRepository studentRepository;

//	public StudentController(StudentRepository studentRepository) {
//		this.studentRepository = studentRepository;
//	}

//	@GetMapping("/getStudent")
//	public StudentDto getStudent() {
//		return new StudentDto(12L, "Ram", "ram@gmail.com");
//	}

//	@GetMapping("/getAllStudents")
//	public List<Student> getStudent() {
//		return studentRepository.findAll();
//	}

	@GetMapping("/get-all-students")
	public List<StudentDto> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/name/{name}")
	public String getStudentByName(@PathVariable String name) {
		return "I'm returning name==> " + name;
	}

	@GetMapping("/{id}")
	public StudentDto getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping("/add-student")
	public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
	}

	@DeleteMapping("/delete-student/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/update-student/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
			@RequestBody AddStudentRequestDto addStudentRequestDto) {
		return ResponseEntity.ok(studentService.updateStudent(id, addStudentRequestDto));
	}

	@PatchMapping("/update-student-partial/{id}")
	public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
		return ResponseEntity.ok(studentService.patchStudent(id, updates));
	}

}
