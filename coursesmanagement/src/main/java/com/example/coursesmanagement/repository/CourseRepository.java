package com.example.coursesmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.coursesmanagement.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	@Query("SELECT u FROM Course u WHERE u.name=?1 AND u.semester=?2 AND u.syllabus=?3")
	Course findCourse(String name, String semester, String sylabus);
	
	@Query("SELECT u FROM Course u")
	List<Course> getAllCourses();
	
	@Query("SELECT u FROM Course u WHERE u.name=?1")
	Course findCourseByName(String name);
	
}
