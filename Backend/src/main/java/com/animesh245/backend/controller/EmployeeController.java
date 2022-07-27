package com.animesh245.backend.controller;

import com.animesh245.backend.exception.EmployeeNotFoundException;
import com.animesh245.backend.model.Employee;
import com.animesh245.backend.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }

    //Get All Employees
    @GetMapping("/employees")
    List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    //Add Employee
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee)
    {
        return employeeRepository.save(newEmployee);
    }

    //Get Single Employee
    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable Long id)
    {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //Update single employee
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(newEmployee.getFirstName());
            employee.setLastName(newEmployee.getLastName());
            employee.setEmail(newEmployee.getEmail());

            return employeeRepository.save(employee);})
                .orElseGet(() -> {
            newEmployee.setId(id);
            return employeeRepository.save(newEmployee);
        });
    }

    //Delete Employee
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id)
    {
        employeeRepository.deleteById(id);
    }
}
