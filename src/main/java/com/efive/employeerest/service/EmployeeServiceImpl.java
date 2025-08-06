package com.efive.employeerest.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.efive.employeerest.dao.EmployeeDAO;
import com.efive.employeerest.entity.Employee;

import jakarta.transaction.Transactional;

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

    @Override
    public Employee findById(int Id) {
        return employeeDAO.findById(Id);
    }

    @Transactional
    @Override
    public Employee save(Employee Employee) {
        return employeeDAO.save(Employee);
    }

    @Transactional
    @Override
    public void deleteById(int Id) {
        employeeDAO.deleteById(Id);
    }

}
