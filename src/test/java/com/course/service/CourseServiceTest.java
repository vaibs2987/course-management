package com.course.service;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.course.mysql.model.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

	@Autowired
	private CourseService courseService;

	@Test
	public void testGetAllCourseByUsers() {
		Page<Course> pages = courseService.getAllCourseByUsers(1l, 0, 1);
		assertTrue(pages.getSize() == 1);
	}

	@Test
	public void testGetAppliedCourseStatus() {
		Map<String, String> resultMap = courseService.getAppliedCourseStatus(1l);
		assertTrue(resultMap.keySet().size() == 2);
	}

	@Test
	public void testApplyForCourse() {
		Set<Long> courseIds = new HashSet<Long>();
		courseIds.add(2l);
		Map<Long, Boolean> resultMap = courseService.applyForCourse(1l, courseIds);
		assertTrue(resultMap.keySet().size() == 1);
	}
}
