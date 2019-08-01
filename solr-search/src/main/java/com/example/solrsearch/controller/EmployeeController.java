package com.example.solrsearch.controller;


import com.example.solrsearch.entity.Employee;
import com.example.solrsearch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST,value="/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        if (employee != null) {
            employeeService.addEmployee(employee);
            return employee;
        }
        return null;

        //return new RequestEntity<Employee>(employee, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByName/{name}")
    public List<Employee> findByName(@PathVariable String name) {
        if (name != null) {
            return employeeService.findByName(name);
        }
        return null;
    }
}
