package com.gznznzjsn.springdatajpatutorial.repository;

import com.gznznzjsn.springdatajpatutorial.entity.Course;
import com.gznznzjsn.springdatajpatutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository materialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .title("Caves")
                .credit(7)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.eee.com")
                .course(course)
                .build();
        materialRepository.save(courseMaterial);
    }
}