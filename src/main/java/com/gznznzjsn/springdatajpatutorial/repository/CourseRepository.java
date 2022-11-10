package com.gznznzjsn.springdatajpatutorial.repository;

import com.gznznzjsn.springdatajpatutorial.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
