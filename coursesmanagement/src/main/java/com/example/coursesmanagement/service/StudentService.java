package com.example.coursesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coursesmanagement.domain.Course;
import com.example.coursesmanagement.domain.Student;
import com.example.coursesmanagement.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired 
	private StudentRepository studentRepository;
	
	
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Student findByIdAndFullname(Integer id, String fullname) {
		return studentRepository.findByIdAndFullname(id, fullname);
	}
	
	public Student findByIdAndCourse(Integer id, String courseName) {
		return studentRepository.findByIdAndEnrolledCourse(id, courseName);
	}
	
	public Student addNewStudent(Student student) {
		Student studentObjToBeSaved= new Student();
		
		studentObjToBeSaved.setId(student.getId());
		studentObjToBeSaved.setCourse(student.getCourse());
		studentObjToBeSaved.setFullname(student.getFullname());
		studentObjToBeSaved.setRegistrationYear(student.getRegistrationYear());
		studentObjToBeSaved.setSemester(student.getSemester());
		
		return studentRepository.save(studentObjToBeSaved);
	}
	
	public Student updateStudentInfo(Student student) {
		return studentRepository.save(student);
	}

	public List<Student> getAllStudentsEnrolledIntoCourse(Course course) {
		return studentRepository.getAllStudentsEnrolledToCourse(course.getName());
	}
	
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}
}
