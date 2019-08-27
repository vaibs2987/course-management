package com.course.model.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.mysql.model.Syllabus;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {

}
