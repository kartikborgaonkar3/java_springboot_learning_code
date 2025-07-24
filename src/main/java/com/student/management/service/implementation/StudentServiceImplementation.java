package com.student.management.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.student.management.dto.StudentDto;
import com.student.management.entity.Student;
import com.student.management.repository.StudentRepository;
import com.student.management.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {
	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		List<StudentDto> studentDtoList = students.stream().
				map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail())).toList();
		//to avoid above conversion from student to studentDto we are using modelMapper.
		return studentDtoList;
	}

	@Override
	public StudentDto getStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Student not found for Id=>" + id));
		
		return modelMapper.map(student, StudentDto.class);
	}
	
	
	
}
