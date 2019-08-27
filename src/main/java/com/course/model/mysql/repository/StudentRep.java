package com.course.model.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.mysql.model.Student;

public interface StudentRep extends JpaRepository<Student, Long> {

	Student getStudentByUserId(Long userId);
}
