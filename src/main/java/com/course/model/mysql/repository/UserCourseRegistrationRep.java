package com.course.model.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.course.mysql.model.UserCourseRegistration;

public interface UserCourseRegistrationRep extends JpaRepository<UserCourseRegistration, Long> {

	@Query(value = "SELECT r from UserCourseRegistration r where r.userId =:userId")
	List<UserCourseRegistration> getRegisteredCourseByUser(@Param("userId") Long userId);
}
