package com.example.coursesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.coursesmanagement.domain.Course;
import com.example.coursesmanagement.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses/new")
	public String createCourseForm(Model model) {
		
		// Create course object to hold course form data
		Course course = new Course();
		model.addAttribute("course", course);
		return "create_course";	
	}
	
	@GetMapping("/courses")
	public String listCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses_main_page";
	}
	
	@PostMapping("/courses")
	public String saveCourse(@ModelAttribute ("course") Course course) {
		
		courseService.addNewCourse(course.getName(),
				course.getSemester(),
				course.getYear(),
				course.getSyllabus(),
				course.getDescription());
		return "redirect:/courses";
	}
	
	@GetMapping("/courses/edit/{id}")
	public String editCourse(@PathVariable Integer id, Model model) {
		model.addAttribute("course", courseService.getCourseById(id));
		return "edit_course";
	}
	
	@PostMapping("/courses/{id}")
	public String updateCourse(@PathVariable Integer id, 
			@ModelAttribute("course") Course course,
			Model model) {
		
		Course existCourse = courseService.getCourseById(id);
		existCourse.setId(course.getId());
		existCourse.setName(course.getName());
		existCourse.setSemester(course.getSemester());
		existCourse.setSyllabus(course.getSyllabus());
		existCourse.setYear(course.getYear());
		existCourse.setDescription(course.getDescription());
		
		courseService.udpateCourse(existCourse);
		return "redirect:/courses";
		
	}
	
	@GetMapping("/courses/{id}")
	public String deleteCourse(@PathVariable Integer id) {
		courseService.deleteCourse(id);
		
		return "redirect:/courses";
	}
	
	
}
