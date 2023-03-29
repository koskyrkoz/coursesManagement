package com.example.coursesmanagement.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "students")
@IdClass(StudentID.class)
public class Student{
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Id
	@Column(name = "course")
	private String course;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "registrationYear")
	private int registrationYear;
	
	@Column(name = "semester")
	private int semester;
	
	
	public Student() {
	}

	public Student(Integer id, String course, String fullname, int registrationYear, int semester) {
		super();
		this.id = id;
		this.course = course;
		this.fullname = fullname;
		this.registrationYear = registrationYear;
		this.semester = semester;
	}

	public Student(StudentID studentID, String fullName, int registrationYear, int semester) {
		this.id = studentID.getId();
		this.course = studentID.getCourse();
		this.fullname = fullName;
		this.registrationYear = registrationYear;
		this.semester = semester;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(int registrationYear) {
		this.registrationYear = registrationYear;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	
}
