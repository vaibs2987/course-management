package com.course.model.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.mysql.model.Instructor;

public interface InstructorRep extends JpaRepository<Instructor, Long>{

}
