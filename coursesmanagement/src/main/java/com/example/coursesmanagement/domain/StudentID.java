package com.example.coursesmanagement.domain;

import java.io.Serializable;
import java.util.Objects;

public class StudentID implements Serializable {
	
	private Integer id;
	private String course;
	
	
	public StudentID() {
	}

	public StudentID(Integer id, String course) {
		this.id = id;
		this.course = course;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	@Override
	public int hashCode() {
		return Objects.hash(course, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentID other = (StudentID) obj;
		return Objects.equals(course, other.course) && Objects.equals(id, other.id);
	}
	
	
	
}
