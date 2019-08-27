package com.course.service;

import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.course.mysql.model.Course;

public interface CourseService {

	Page<Course> getAllCourseByUsers(Long userId, int page, int size);

	Map<Long, Boolean> applyForCourse(Long userId, Set<Long> courseIds);

	Map<String, String> getAppliedCourseStatus(Long userId);
}
