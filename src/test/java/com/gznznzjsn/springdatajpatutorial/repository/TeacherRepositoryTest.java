package com.gznznzjsn.springdatajpatutorial.repository;

import com.gznznzjsn.springdatajpatutorial.entity.Course;
import com.gznznzjsn.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course math = Course.builder()
                .title("Math")
                .credit(5)
                .build();
        Course eng = Course.builder()
                .title("English")
                .credit(6)
                .build();
        Teacher teacher = Teacher.builder()
                .firstName("Ya")
                .lastName("Po")
//                .courses(List.of(math,eng))
                .build();
        teacherRepository.save(teacher);

    }
}