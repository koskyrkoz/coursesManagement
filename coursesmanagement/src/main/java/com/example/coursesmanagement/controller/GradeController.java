package com.example.coursesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.coursesmanagement.domain.Grade;
import com.example.coursesmanagement.service.GradeService;

@Controller
public class GradeController {
	
	@Autowired
	private GradeService gradeService;
	
	
	@GetMapping("/courses/{course_name}/students/student/{id}/grades/add_grade")
	public String showNewGradeForm(@PathVariable("course_name") String course_name,
			@PathVariable("id") Integer id,
			Model model) {
		Grade grade = new Grade();
		grade.setCourseName(course_name);
		grade.setStudentID(id);
		model.addAttribute("grade", grade);
		return "student_grade_tab_add_new_grade";
	}
	
	@PostMapping("/courses/{course_name}/students/student/{id}/grades/add_grade")
	public String addStudentGrade(@PathVariable ("course_name") String course_name,
			@PathVariable ("id") Integer id,
			@ModelAttribute ("grade") Grade grade) {
		gradeService.addNewGrade(course_name,
				id,
				grade.getProjectGrade(),
				grade.getExamGrade());
		
		return "redirect:/courses/{course_name}/students/student/{id}/grades";
	}
	
	@GetMapping("/courses/{course_name}/students/student/{id}/grades")
	public String enterGradeTab(@PathVariable ("course_name") String course_name,
			@PathVariable ("id") Integer id,
			Model model) {
		
		Grade grade = gradeService.findGrade(course_name, id);
		
		// If grade entry is not found in the database, create new grade object
		if (grade == null) {
			grade = new Grade();
			grade.setCourseName(course_name);
			grade.setStudentID(id);
		}
				
		model.addAttribute("grade", grade);
		return "student_grade_tab";
	}
		
}
