package com.example.coursesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coursesmanagement.domain.Grade;
import com.example.coursesmanagement.repository.GradeRepository;

@Service
public class GradeService {
	
	@Autowired
	private GradeRepository gradeRepository;

	public GradeService(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}
	
	public Grade addNewGrade(String courseName,
			Integer studentId,
			Integer projectGrade,
			Integer examGrade) {
		if (courseName!=null && studentId!=null) {
			Grade grade = new Grade();
			grade.setCourseName(courseName);
			grade.setStudentID(studentId);
			grade.setExamGrade(examGrade);
			grade.setProjectGrade(projectGrade);
			
			return gradeRepository.save(grade);
		}else
			return null;
	}
	
	public Grade findGrade(String courseName, Integer studentId) {
		return gradeRepository.findByCourseNameStudentID(courseName, studentId);
	}
	
}
