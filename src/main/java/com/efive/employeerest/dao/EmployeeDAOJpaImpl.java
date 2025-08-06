package com.efive.employeerest.dao;

import com.efive.employeerest.entity.Employee;
import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager entityManager;

    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee",
                Employee.class);

        return query.getResultList();
    }

    @Override
    public Employee findById(int Id) {
        return entityManager.find(Employee.class, Id);
    }

    @Override
    public Employee save(Employee Employee) {
        return entityManager.merge(Employee);
    }

    @Override
    public void deleteById(int Id) {
        Employee employee = entityManager.find(Employee.class, Id);
        entityManager.remove(employee);
    }

}
