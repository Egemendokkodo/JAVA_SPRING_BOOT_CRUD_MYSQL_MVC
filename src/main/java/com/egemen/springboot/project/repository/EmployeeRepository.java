package com.egemen.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egemen.springboot.project.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    
}
