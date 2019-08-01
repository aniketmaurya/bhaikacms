package com.example.solrsearch.service;

import com.example.solrsearch.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> findByName(String name);
}
