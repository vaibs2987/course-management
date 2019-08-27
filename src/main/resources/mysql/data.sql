create database course_management;
use course_management;

CREATE  TABLE IF NOT EXISTS `course_management`.`user` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `name` VARCHAR(500) NOT NULL ,
  `user_name` VARCHAR(500) NOT NULL ,
  `password` VARCHAR(500) NOT NULL ,
  `created_at` DATETIME ,
  `last_login_at` DATETIME ,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;
  
   CREATE  TABLE IF NOT EXISTS `course_management`.`role` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;
  
  CREATE  TABLE IF NOT EXISTS `course_management`.`user_role` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `user_id` bigint(20) NOT NULL ,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;
  
  
CREATE  TABLE IF NOT EXISTS `course_management`.`student` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
`user_id` bigint(20) not null,
`current_semester_id` bigint(20) not null,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;
  

 CREATE  TABLE IF NOT EXISTS `course_management`.`semester` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `name` VARCHAR(1000) NOT NULL ,
  `branch_code` varchar(50) ,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `course_management`.`course` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `name` VARCHAR(1000) NOT NULL ,
  `description` text ,
  `start_date` DATETIME ,
  `status` VARCHAR(50) ,
  `image_path` VARCHAR(500) ,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `course_management`.`course_semester_map` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `semester_id` bigint(20) NOT NULL ,
  `course_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `course_management`.`syllabus` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `course_id` bigint(20) not null,
  `instructor_id` bigint(20) not null,
  `name` VARCHAR(500) NOT NULL ,
  `description` text ,
  `ordinal` int(11) ,
  `created_at` datetime ,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;

 CREATE  TABLE IF NOT EXISTS `course_management`.`instructor` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `user_id` bigint(20) NOT NULL ,
  `name` VARCHAR(1000) NOT NULL ,
  `image_url` varchar(500) ,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `course_management`.`study_material` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `syllabus_id` bigint(20) not null,
  `file_name` VARCHAR(500) NOT NULL ,
  `description` VARCHAR(500) ,
  `file_type` VARCHAR(50) ,
  `ordinal` int(11) ,
  `created_at` datetime ,
  PRIMARY KEY (`id`) )ENGINE = InnoDB;



  
  
  CREATE  TABLE IF NOT EXISTS `course_management`.`user_course_registration` (
  `id` BIGINT(20) NOT NULL  auto_increment ,
  `user_id` bigint(20) not null,
  `course_id` bigint(20) not null,
  `semester_id` bigint(20) not null,
  `applied_date` DATETIME ,
  `approved_date` DATETIME ,
  `status` VARCHAR(50) ,
  `approved_by` bigint(20),
  PRIMARY KEY (`id`) )ENGINE = InnoDB;
  
  alter table user_course_registration add unique index(user_id,course_id,semester_id);