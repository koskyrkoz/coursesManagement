package com.example.coursesmanagement.domain;

import java.io.Serializable;
import java.util.Objects;

public class GradeID implements Serializable{
	
	private String courseName;
	private Integer studentID;
	
	
	public GradeID() {
	}

	public GradeID(String courseName, Integer studentID) {
		super();
		this.courseName = courseName;
		this.studentID = studentID;
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

	@Override
	public int hashCode() {
		return Objects.hash(courseName, studentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradeID other = (GradeID) obj;
		return Objects.equals(courseName, other.courseName) && Objects.equals(studentID, other.studentID);
	}

	
}
