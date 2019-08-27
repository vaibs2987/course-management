package com.course.controller;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.client.model.CourseApplied;
import com.course.mysql.model.Course;
import com.course.service.CourseService;
import com.course.util.ApiResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/course/")
@Api(value = "Course Management System")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@ApiOperation(value = "Get all the courses for given user")
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ApiResponse<Page<Course>> getAllUserCourses(@RequestParam Long userId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Course> pages = courseService.getAllCourseByUsers(userId, page, size);
		return new ApiResponse<Page<Course>>(pages);
	}

	@ApiOperation(value = "Apply for the courses for given user")
	@RequestMapping(value = "apply", method = RequestMethod.POST)
	public ApiResponse<Map<Long, Boolean>> applyForCourse(@RequestParam Long userId,
			@NotNull @RequestBody CourseApplied applied) {
		Map<Long, Boolean> resultMap = courseService.applyForCourse(userId, applied.getIds());
		return new ApiResponse<Map<Long, Boolean>>(resultMap);
	}

	@ApiOperation(value = "Check the status of all the courses for given user")
	@RequestMapping(value = "status", method = RequestMethod.GET)
	public ApiResponse<Map<String, String>> getAppliedCourseStatus(@RequestParam Long userId) {
		Map<String, String> resultMap = courseService.getAppliedCourseStatus(userId);
		return new ApiResponse<Map<String, String>>(resultMap);
	}
}