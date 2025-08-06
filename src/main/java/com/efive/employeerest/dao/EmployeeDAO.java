package com.efive.employeerest.dao;

import com.efive.employeerest.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
