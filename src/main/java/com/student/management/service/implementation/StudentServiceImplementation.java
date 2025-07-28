package com.student.management.service.implementation;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.student.management.dto.AddStudentRequestDto;
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
		List<StudentDto> studentDtoList = students.stream()
				.map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail())).toList();
		// to avoid above conversion from student to studentDto we are using
		// modelMapper.
		return studentDtoList;
	}

	@Override
	public StudentDto getStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found for Id=>" + id));

		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
		Student newStudent = modelMapper.map(addStudentRequestDto, Student.class); // this will be in java memory
		Student student = studentRepository.save(newStudent); // this will get save in DB
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public void deleteStudentById(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new IllegalArgumentException("Student does not exist for id " + id);
		}
		studentRepository.deleteById(id);
	}

	@Override
	public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student does not exists with id " + id));
		modelMapper.map(addStudentRequestDto, student);
		student = studentRepository.save(student);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto patchStudent(Long id, Map<String, Object> updates) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student does not exists with id " + id));

		updates.forEach((string, object) -> {
			switch (string) {
			case "name":
				student.setName((String) object);
				break;

			case "email":
				student.setEmail((String) object);
				break;

			default:
				throw new IllegalArgumentException("Field is not supported");
			}
		});

		Student updatedStudent = studentRepository.save(student);
		return modelMapper.map(updatedStudent, StudentDto.class);
	}

}
