package com.gznznzjsn.springboottutorial.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.gznznzjsn.springboottutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

//TODO
@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("IM")
                .departmentAddress("Mogilev")
                .build();

        entityManager.persist(department);
    }

    @Test
    void findByIdAndReturnDepartment() {
        Department department = departmentRepository.findById(1L).get();
        assertEquals("Mechanical Engineering",department.getDepartmentName());
    }
}