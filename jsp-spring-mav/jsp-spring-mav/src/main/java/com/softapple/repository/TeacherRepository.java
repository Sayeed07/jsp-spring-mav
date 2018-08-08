package com.softapple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softapple.model.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
