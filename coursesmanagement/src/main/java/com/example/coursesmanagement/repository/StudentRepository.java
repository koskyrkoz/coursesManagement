package com.example.coursesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.coursesmanagement.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	@Query("SELECT u FROM Student u WHERE u.id=?1 AND u.fullname=?2")
	Student findByIdAndFullname(Integer id, String fullName);
	
	@Query("SELECT u FROM Student u WHERE u.id=?1 AND u.course=?2")
	Student findByIdAndEnrolledCourse(Integer id, String courseName);
	
	@Query("SELECT u FROM Student u WHERE u.course=?1")
	List<Student> getAllStudentsEnrolledToCourse(String courseName);
	
}
