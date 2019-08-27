package com.course.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.course.model.mysql.repository.CourseRep;
import com.course.model.mysql.repository.StudentRep;
import com.course.model.mysql.repository.UserCourseRegistrationRep;
import com.course.mysql.model.Course;
import com.course.mysql.model.CourseStatus;
import com.course.mysql.model.Student;
import com.course.mysql.model.UserCourseRegistration;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRep courseRep;

	@Autowired
	private UserCourseRegistrationRep userCourseRegistrationRep;

	@Autowired
	private StudentRep studentRep;

	@Override
	public Page<Course> getAllCourseByUsers(Long userId, int page, int size) {
		Pageable pageable = new PageRequest(page, size);
		Page<Course> courses = courseRep.getAllCourseByUsers(userId, pageable);
		return courses;
	}

	@Override
	public Map<Long, Boolean> applyForCourse(Long userId, Set<Long> courseIds) {
		Set<Integer> ids = courseRep.getAllCourseIds(userId);
		Student student = studentRep.getStudentByUserId(userId);

		if (ids == null || student == null) {
			return null;
		}
		Map<Long, Boolean> resultMap = new HashMap<Long, Boolean>();
		for (Long id : courseIds) {
			boolean status = false;
			if (ids.contains(id.intValue())) {
				UserCourseRegistration registration = saveObject(id, student.getCurrentSemesterId(), userId);
				status = registration.getId() != null;
			}
			resultMap.put(id, status);
		}
		return resultMap;
	}

	private UserCourseRegistration saveObject(Long courseId, Long semesterId, Long userId) {
		UserCourseRegistration registration = new UserCourseRegistration();
		registration.setAppliedDate(new Date());
		registration.setCourseId(courseId);
		registration.setSemesterId(semesterId);
		registration.setUserId(userId);
		registration.setStatus(CourseStatus.APPLIED.name());
		try {
			registration = userCourseRegistrationRep.save(registration);
		} catch (DataIntegrityViolationException e) {
		}
		return registration;
	}

	@Override
	public Map<String, String> getAppliedCourseStatus(Long userId) {
		List<UserCourseRegistration> registrations = userCourseRegistrationRep.getRegisteredCourseByUser(userId);
		if (registrations == null) {
			return null;
		}
		Map<String, String> resultMap = new HashMap<String, String>();
		for (UserCourseRegistration registration : registrations) {
			Course course = registration.getCourse();
			resultMap.put(course.getId() + " : " + course.getName(), registration.getStatus());
		}
		return resultMap;
	}

}
