package com.softapple.student.model.dto;

public class StudentDTO {	
	
	private Long studentId;
	
	private String studentName;
	
	private Integer studentRoll;
	
	private String photoName;
	
	private Long teacherId;

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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}	
	
}
