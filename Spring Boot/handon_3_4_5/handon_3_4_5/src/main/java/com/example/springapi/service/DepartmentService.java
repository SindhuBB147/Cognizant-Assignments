package com.example.springapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springapi.dao.DepartmentDao;
import com.example.springapi.model.Department;

@Service
public class DepartmentService {

    private final DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
