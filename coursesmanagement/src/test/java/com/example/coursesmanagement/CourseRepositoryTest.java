package com.example.coursesmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.coursesmanagement.domain.Course;
import com.example.coursesmanagement.repository.CourseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CourseRepositoryTest {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	/*
	 * Successfull JUnit test => Change application.properties -> spring.jpa.hibernate.ddl-auto=create
	 */
	@Test
	public void testCreateCourse() {
		Course course = new Course();
		course.setName("Software Development");
		course.setSemester("spring");
		course.setYear(4);
		course.setSyllabus("MYY403");
		course.setDescription("This course focuses on issues related to software lifecycle");
	
		Course savedCourse = courseRepository.save(course);
		
		Course existCourse = entityManager.find(Course.class, savedCourse.getId());
		
		assertThat(existCourse.getName().equals(course.getName()));
		assertThat(existCourse.getSemester().equals(course.getSemester()));
		assertThat(existCourse.getYear() == (course.getYear()));
		assertThat(existCourse.getSyllabus().equals(course.getSyllabus()));
	}
	
	
	/*
	 * Successfull JUnit test => Change application.properties -> spring.jpa.hibernate.ddl-auto=none
	 */
	@Test
	public void testFindCourse() {
		String courseName = "Software Development";
		String courseSemester = "spring";
		String courseSyllabus = "MYY403";
		
		Course course = courseRepository.findCourse(courseName, courseSemester, courseSyllabus);
		
		assertThat(course).isNotNull();
	}
	
	@Test
	public void testDeleteCourse() {
		Course course = new Course();
		course.setName("Software Development");
		course.setSemester("spring");
		course.setYear(4);
		course.setSyllabus("MYY403");
		course.setDescription("This course focuses on issues related to software lifecycle");
		
		Course existCourse = courseRepository.findCourse(course.getName(), course.getSemester(), course.getSyllabus());
		Integer existCourseID = existCourse.getId();
		assertThat(existCourse).isNotNull();
		
		courseRepository.delete(course);
		
		Course courseToSearch = courseRepository.getById(existCourseID);
		assertThat(courseToSearch).isNull();
		
	}

}
