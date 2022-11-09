package com.gznznzjsn.springboottutorial.service;

import com.gznznzjsn.springboottutorial.entity.Department;
import com.gznznzjsn.springboottutorial.exception.DepartmentNotFoundException;
import com.gznznzjsn.springboottutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if(departmentOptional.isEmpty()){
            throw new DepartmentNotFoundException("Department not Available");
        }
        return departmentOptional.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department departmentInDB = departmentRepository.findById(departmentId).orElseThrow();
        if (Objects.nonNull(department.getDepartmentName())
                && !department.getDepartmentName().trim().isEmpty()) {
            departmentInDB.setDepartmentName(department.getDepartmentName());
        }
        if (Objects.nonNull(department.getDepartmentAddress())
                && !department.getDepartmentAddress().trim().isEmpty()) {
            departmentInDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        if (Objects.nonNull(department.getDepartmentCode())
                && !department.getDepartmentCode().trim().isEmpty()) {
            departmentInDB.setDepartmentCode(department.getDepartmentCode());
        }
        return departmentRepository.save(departmentInDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        //what if more than one
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
