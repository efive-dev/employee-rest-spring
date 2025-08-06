package com.efive.employeerest.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.efive.employeerest.dao.EmployeeRepository;
import com.efive.employeerest.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int Id) {
        return employeeRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Did not find employee " + Id));
    }

    @Override
    public Employee save(Employee Employee) {
        return employeeRepository.save(Employee);
    }

    @Override
    public void deleteById(int Id) {
        employeeRepository.deleteById(Id);
    }

}
