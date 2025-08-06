package com.efive.employeerest.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.efive.employeerest.entity.Employee;
import com.efive.employeerest.service.EmployeeService;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null)
            throw new RuntimeException("Employee id not found " + employeeId);

        return employee;
    }

    @PostMapping("/employees")
    public Employee postMethodName(@RequestBody Employee employee) {
        employee.setId(0);

        Employee employeeDB = employeeService.save(employee);
        return employeeDB;
    }

}
