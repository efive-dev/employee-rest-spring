package com.efive.employeerest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.efive.employeerest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
