package com.softapple.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable{	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
		
	@Column(name="student_name")
	private String studentName;
	
	@Column(name="student_roll")
	private Integer studentRoll;
	
	@Column(name="photo_name")
	private String photoName;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;
	
	@Column(name="custom_student_id")
	private String customStudentId;	

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStudentRoll() {
		return studentRoll;
	}

	public void setStudentRoll(Integer studentRoll) {
		this.studentRoll = studentRoll;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCustomStudentId() {
		return customStudentId;
	}

	public void setCustomStudentId(String customStudentId) {
		this.customStudentId = customStudentId;
	}
	
}
