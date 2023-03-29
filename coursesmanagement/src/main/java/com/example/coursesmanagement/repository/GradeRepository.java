package com.example.coursesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.coursesmanagement.domain.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM GRADES u WHERE u.course_name=?1 AND u.student_id=?2")
	Grade findByCourseNameStudentID(String courseName, Integer studentID);
		
}
