package com.example.coursesmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.example.coursesmanagement.domain.Course;
import com.example.coursesmanagement.domain.Student;
import com.example.coursesmanagement.domain.StudentID;
import com.example.coursesmanagement.repository.StudentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	/*
	 * Successfull JUnit test => Change application.properties -> spring.jpa.hibernate.ddl-auto=create
	 */
	@Test
	void testCreateStudent() {
		StudentID student1ID = new StudentID(3567,
				"Software Engineering");
		StudentID student2ID = new StudentID(3567,
				"Algebra");
		StudentID student3ID = new StudentID(4100,
				"Software Engineering");
		
		Student student1 = new Student(student1ID,
				"Takis Chatzis",
				2014,
				16); 
		Student student2 = new Student(student2ID,
				"Takis Chatzis",
				2014,
				16);
		Student student3 = new Student(student3ID,
				"Dionisios Nikou",
				2018,
				12);
				
		Student savedStudent1 = studentRepository.save(student1);
		Student savedStudent2 = studentRepository.save(student2);
		Student savedStudent3 = studentRepository.save(student3);
		
		Student existStudent = studentRepository.findByIdAndEnrolledCourse(3567, "Software Engineering");
		
		assertThat(existStudent.getId().equals(savedStudent1.getId()));
		assertThat(existStudent.getCourse().equals(savedStudent1.getCourse()));
		assertThat(existStudent.getFullname().equals(savedStudent1.getFullname()));
		assertThat(existStudent.getRegistrationYear()==savedStudent1.getRegistrationYear());
		assertThat(existStudent.getSemester()==savedStudent1.getSemester());
	}

	
	/*
	 * Successfull JUnit test => Change application.properties -> spring.jpa.hibernate.ddl-auto=none
	 */
	@Test
	void testFindStudent() {
		Integer studentID = 3567;
		String studentFullName = "Takis Chatzis";
		Integer studentRegistrationYear = 2014;
		String studentSemeter = "spring";
		
		Course course = new Course();
		course.setName("Software Engineering");
		course.setSemester("spring");
		course.setYear(4);
		course.setSyllabus("MYY803");
		course.setDescription("This course focuses on issues related to software lifecycle");
		
		Student student = studentRepository.findByIdAndEnrolledCourse(studentID, course.getName());
	
		assertThat(student).isNotNull();
	}
	
	/*
	 * Successfull JUnit test => Change application.properties -> spring.jpa.hibernate.ddl-auto=none
	 */
	@Test
	void testGetAllStudentsEnrolledToCourse() {
		List<Student> specificCourseStudents = studentRepository.getAllStudentsEnrolledToCourse("Software Engineering");
		
		assertThat(specificCourseStudents.size()).isEqualTo(2);
	}
	
}
