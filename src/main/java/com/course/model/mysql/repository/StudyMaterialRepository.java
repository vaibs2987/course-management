package com.course.model.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.mysql.model.StudyMaterial;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long>{

}
