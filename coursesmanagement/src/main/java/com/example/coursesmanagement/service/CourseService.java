package com.example.coursesmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coursesmanagement.domain.Course;
import com.example.coursesmanagement.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepo;
	
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}
	
	public Course addNewCourse(String name, String semester, int year, String syllabus, String description) {
		if (name != null && semester != null && year !=0 && syllabus != null) {
			Course course = new Course();
			
			course.setName(name);
			course.setSemester(semester);
			course.setYear(year);
			course.setSyllabus(syllabus);
			course.setDescription(description);
			
			return courseRepo.save(course);
		}else
			return null;
	}
	
	/**
	 * @param name String that holds the name of the course
	 * @param semester String that holds the semester of the course
	 * @param syllabus String that holds the syllabus of the course
	 * @return Course object from DB with the above columns' values
	 */
	public Course findCourse(String name, String semester, String syllabus) {
		return courseRepo.findCourse(name, semester, syllabus);
	}
	
	public Course findCourseByName(String name) {
		return courseRepo.findCourseByName(name);
	}
	
	
	/**
	 * @return List of Course objects that are stored in the DB
	 */
	public List<Course> getAllCourses() {
		return courseRepo.getAllCourses();
	}
	
	/**
	 * @param id Integer of the course's unique id
	 * @return Course object with the above unique id from the DB
	 */
	public Course getCourseById(Integer id) {
		return courseRepo.findById(id).get();
	}
	
	public Course udpateCourse(Course course) {
		return courseRepo.save(course);
	}
	
	public void deleteCourse(Integer id) {
		courseRepo.deleteById(id);
	}
}
