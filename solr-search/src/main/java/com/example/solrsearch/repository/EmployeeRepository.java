package com.example.solrsearch.repository;

import com.example.solrsearch.entity.Employee;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends SolrCrudRepository<Employee,Long> {
    List<Employee> findByName(String name);
}
