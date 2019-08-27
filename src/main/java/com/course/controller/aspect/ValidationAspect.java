package com.course.controller.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.course.exception.InvalidParamException;

@Aspect
@Component
public class ValidationAspect {

	@Before("execution(* com.course.controller.CourseController.getAllUserCourses(..))")
	public void validatePayload(JoinPoint joinPoint) throws Throwable {
		Long userId = (Long) joinPoint.getArgs()[0];
		if (userId == null || userId == 0) {
			throw new InvalidParamException("UserId is not correct");
		}
	}
}
