package com.example.coursesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.coursesmanagement.domain.Course;
import com.example.coursesmanagement.domain.Student;
import com.example.coursesmanagement.service.CourseService;
import com.example.coursesmanagement.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/courses/{course_name}/new_student")
	public String showNewStudentForm(@PathVariable ("course_name") String course_name,
			Model model) {
		
		Student student = new Student();
		student.setCourse(course_name);
		model.addAttribute("student", student);
		return "new_student_form";
	}
	
	@PostMapping("/courses/{course_name}/new")
	public String addNewStudent(@PathVariable ("course_name") String course_name,
			@ModelAttribute ("student") Student student) {
		student.setCourse(course_name);
		studentService.addNewStudent(student);
		
		return "redirect:/courses";
	}
	
	
	@GetMapping("/courses/{course_name}/students")
	public String getAllStudents(@PathVariable String course_name,
			Model model) {
		Course selectedCourse = courseService.findCourseByName(course_name);
		model.addAttribute("students", studentService.getAllStudentsEnrolledIntoCourse(selectedCourse));
		return "students_page";
	}
	
	@GetMapping("/courses/{course_name}/students/student/{id}/delete")
	public String deleteStudent(@PathVariable String course_name,
			@PathVariable Integer id,
			Model model) {
		
		Student student = studentService.findByIdAndCourse(id, course_name);
		if (student == null) {
			student = new Student();
			student.setCourse(course_name);
			student.setId(id);
		}
		
		System.out.println("Student info: ID-> "+student.getId()+" | Course-> "+student.getCourse());

		studentService.deleteStudent(student);
		return "redirect:/courses/{course_name}/students";
	}
	
	@GetMapping("/courses/{course_name}/students/student/{id}/edit_student_info")
	public String showUpdateStudentTab(@PathVariable String course_name,
			@PathVariable Integer id,
			Model model) {
		
		Student student = studentService.findByIdAndCourse(id, course_name); 
		
		model.addAttribute("student", student);	
		
		System.out.println("GET_MAPPING : "+student + " || "+student.getCourse()+" | "+student.getId());
		return "students_page_edit_student_info";
	}
	
	
	@PostMapping("/courses/{course_name}/students/student/{id}/edit_student_info")
	public String updateStudentInfo(@PathVariable String course_name,
			@PathVariable Integer id,
			@ModelAttribute("student") Student student,
			Model model) {
		
		student.setCourse(course_name);
		
		System.out.println("POST_MAPPING : "+student + " || "+student.getCourse()+" | "+student.getId());		
		Student existStudent = studentService.findByIdAndCourse(id, course_name);
		existStudent.setFullname(student.getFullname());
		existStudent.setId(student.getId());
		existStudent.setCourse(student.getCourse());
		existStudent.setRegistrationYear(student.getRegistrationYear());
		existStudent.setSemester(student.getSemester());
		
		studentService.updateStudentInfo(existStudent);
		
		return "redirect:/courses/{course_name}/students";
	}
	
}
