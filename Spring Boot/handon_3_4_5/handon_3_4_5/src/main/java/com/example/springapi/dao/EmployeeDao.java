package com.example.springapi.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.example.springapi.model.Employee;
import com.example.springapi.service.exception.EmployeeNotFoundException;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);
    
    private static List<Employee> EMPLOYEE_LIST = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public EmployeeDao(ApplicationContext context) {
        LOGGER.info("Initializing EmployeeDao and loading static employee list from XML");
        EMPLOYEE_LIST = (List<Employee>) context.getBean("employeeList");
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public void updateEmployee(Employee updatedEmployee) throws EmployeeNotFoundException {
        LOGGER.info("START updateEmployee: {}", updatedEmployee.getId());
        boolean found = false;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            Employee emp = EMPLOYEE_LIST.get(i);
            if (emp.getId().equals(updatedEmployee.getId())) {
                EMPLOYEE_LIST.set(i, updatedEmployee);
                found = true;
                break;
            }
        }
        if (!found) {
            LOGGER.error("Employee not found with ID: {}", updatedEmployee.getId());
            throw new EmployeeNotFoundException("Employee with id " + updatedEmployee.getId() + " not found.");
        }
        LOGGER.info("END updateEmployee");
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        LOGGER.info("START deleteEmployee: {}", id);
        boolean removed = false;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(id)) {
                EMPLOYEE_LIST.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed) {
            LOGGER.error("Employee not found for deletion with ID: {}", id);
            throw new EmployeeNotFoundException("Employee with id " + id + " not found.");
        }
        LOGGER.info("END deleteEmployee");
    }
}
