package com.egemen.springboot.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.egemen.springboot.project.exception.ResourceNotFoundException;
import com.egemen.springboot.project.model.Employee;
import com.egemen.springboot.project.repository.EmployeeRepository;
import com.egemen.springboot.project.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee) ;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id); 
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new ResourceNotFoundException("Employee","Id",id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        
        // database te kullanıcı var mı yok mu kontrol et
        Employee existingEmployee=employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName()); // fonksiyona gönderilen yeni bilgileri burada önce veritabanından ilgiili kullanıcı alıp sonra set ediyoruz
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        
        // update edilmiş kullanıcı veritabanına kaydediyoruz.
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        // database te kullanıcı var mı yok mu kontrol et
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));




        employeeRepository.deleteById(id);
    }
    
}
