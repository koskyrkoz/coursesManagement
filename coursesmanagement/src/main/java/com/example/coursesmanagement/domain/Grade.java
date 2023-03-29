package com.example.coursesmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "grades")
@IdClass(GradeID.class)
public class Grade {
	
	@Id
	@Column(name = "student_id")
	private Integer studentID;
	
	@Id
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "project_grade")
	private Integer projectGrade;
	
	@Column(name = "exam_grade")
	private Integer examGrade;
	
	public Grade() {
		
	}

	public Grade(String courseName, Integer studentID, Integer projectGrade, Integer examGrade) {
		this.courseName = courseName;
		this.studentID = studentID;
		this.projectGrade = projectGrade;
		this.examGrade = examGrade;
	}
	
	public Grade(GradeID gradeID, Integer projectGrade, Integer exameGrade) {
		this.courseName = gradeID.getCourseName();
		this.studentID = gradeID.getStudentID();
		this.examGrade = exameGrade;
		this.projectGrade = projectGrade;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getProjectGrade() {
		return projectGrade;
	}

	public void setProjectGrade(Integer projectGrade) {
		this.projectGrade = projectGrade;
	}

	public Integer getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(Integer examGrade) {
		this.examGrade = examGrade;
	}
	
	
}
