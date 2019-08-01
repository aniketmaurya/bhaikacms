package com.example.solrsearch.service.Imp;

import com.example.solrsearch.entity.Employee;
import com.example.solrsearch.repository.EmployeeRepository;
import com.example.solrsearch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImp implements EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public List<Employee> findByName(String name)
    {
        return employeeRepository.findByName(name);
    }
}

