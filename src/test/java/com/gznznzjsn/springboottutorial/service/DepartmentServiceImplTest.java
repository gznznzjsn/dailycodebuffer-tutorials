package com.gznznzjsn.springboottutorial.service;

import com.gznznzjsn.springboottutorial.entity.Department;
import com.gznznzjsn.springboottutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO
@SpringBootTest
class DepartmentServiceImplTest {
    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Minsk")
                .departmentCode("IM")
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }


    @Test
    @DisplayName("Get department based on valid departmentName")
    @Disabled
    void fetchDepartmentByValidName() {
        String departmentName = "IT";
        Department foundDepartment = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, foundDepartment.getDepartmentName());
    }
}