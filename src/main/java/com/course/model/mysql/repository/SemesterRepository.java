package com.course.model.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.mysql.model.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long>{

}
