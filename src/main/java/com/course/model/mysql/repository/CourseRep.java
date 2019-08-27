package com.course.model.mysql.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.course.mysql.model.Course;

public interface CourseRep extends JpaRepository<Course, Long> {

	@Query(value = "SELECT c from Course c, Student s, CourseSemesterMap cdm where s.userId=:userId and "
			+ "cdm.courseId = c.id and s.currentSemesterId = cdm.semesterId")
	Page<Course> getAllCourseByUsers(@Param("userId") Long userId, Pageable pageable);
	
	@Query(value = "SELECT c.id from Course c, Student s, CourseSemesterMap cdm where s.userId=:userId and "
			+ "cdm.courseId = c.id and s.currentSemesterId = cdm.semesterId")
	Set<Integer> getAllCourseIds(@Param("userId") Long userId);
}
