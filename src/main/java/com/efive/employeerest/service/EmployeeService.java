package com.efive.employeerest.service;

import java.util.List;
import com.efive.employeerest.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
}
