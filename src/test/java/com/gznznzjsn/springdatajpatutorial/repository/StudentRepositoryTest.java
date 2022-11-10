package com.gznznzjsn.springdatajpatutorial.repository;

import com.gznznzjsn.springdatajpatutorial.entity.Guardian;
import com.gznznzjsn.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Disabled
@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("gz@gmail.com")
                .firstName("T")
                .lastName("L")
//                .guardianName("P")
//                .guardianEmail("zn@email.com")
//                .guardianMobile("000000000")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("P")
                .email("zn@email.com")
                .mobile("000000000")
                .build();
        Student student = Student.builder()
                .emailId("gz2@gmail.com")
                .firstName("T2")
                .lastName("L2")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> list = studentRepository.findAll();
        System.out.println(list);
    }

    @Test
    public void printStudentByFirstName() {
        System.out.println(studentRepository.findStudentByFirstName("T"));
    }

    @Test
    public void printStudentByPartOfFirstName() {
        System.out.println(studentRepository.findStudentByFirstNameContaining("T"));
    }

    @Test
    public void printStudentByNotNullLastName() {
        System.out.println(studentRepository.findStudentByLastNameNotNull());
    }

    @Test
    public void printStudentByGuardianName() {
        System.out.println(studentRepository.findStudentByGuardianName("P"));
    }

    @Test
    public void printStudentByEmailAddress() {
        System.out.println(studentRepository.getStudentByEmailAddress("gz@gmail.com"));
    }
    @Test
    public void printStudentByEmailAddressNative() {
        System.out.println(studentRepository.getStudentByEmailAddressNative("gz@gmail.com"));
    }
    @Test
    public void printStudentByEmailAddressNativeWithParam() {
        System.out.println(studentRepository.getStudentByEmailAddressNativeWithParam("gz@gmail.com"));
    }

    @Test
    public void printStudentFirstNameByEmailAddress() {
        System.out.println(studentRepository.getStudentFirstNameByEmailAddress("gz@gmail.com"));
    }
    @Test
    public void updateStudentNameByEmailId() {
        System.out.println(studentRepository.updateStudentNameByEmailId("T_NEW","gz@gmail.com"));
    }


}