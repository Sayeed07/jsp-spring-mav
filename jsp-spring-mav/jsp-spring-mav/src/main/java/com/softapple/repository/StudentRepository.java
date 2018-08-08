package com.softapple.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softapple.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	List<Student> findByStudentNameAndTeacher_TeacherName(String studentName, String teacherName);


}
