package com.efive.employeerest.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.efive.employeerest.dao.EmployeeDAO;
import com.efive.employeerest.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

}
