package com.egemen.springboot.project.service;



import java.util.List;

import com.egemen.springboot.project.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);   
    Employee updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);
}
