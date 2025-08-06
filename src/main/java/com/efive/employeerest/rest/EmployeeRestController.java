package com.efive.employeerest.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.efive.employeerest.entity.Employee;
import com.efive.employeerest.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
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

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
            @RequestBody Map<String, Object> patchPayload) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null)
            throw new RuntimeException("Employee id not found " + employeeId);

        if (patchPayload.containsKey("id"))
            throw new RuntimeException("Employee id not allowed in request body " + employeeId);

        Employee patchedEmployee = apply(patchPayload, employee);

        return employeeService.save(patchedEmployee);
    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee) {
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);
        ObjectNode patchPayloadNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        employeeNode.setAll(patchPayloadNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null)
            throw new RuntimeException("Employee id not found " + employeeId);

        employeeService.deleteById(employeeId);

        return "Deleted employee id " + employeeId;
    }
}
