package com.egemen.springboot.project.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egemen.springboot.project.model.Employee;
import com.egemen.springboot.project.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // apiye post işlemiyle veritabanına veri kaydediyoruz.
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.CREATED); 
    }

    // apiden tüm kullanıcıları çekiyoruz.
    @GetMapping
    public List<Employee>  getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // apiden kullanıcıları idlerine göre çekiyoruz. 
    // http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable("id")long id){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    // kullanıcıları update işlemi yapma
    // http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    // kullanıcı silme 
    // http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id ){
        
        // veritabanından sil
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
    }


}
