package com.animesh245.backend.controller;

import com.animesh245.backend.exception.EmployeeNotFoundException;
import com.animesh245.backend.model.Employee;
import com.animesh245.backend.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "http://localhost:4200")// when modules are run separately
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
    ResponseEntity<Employee> getEmployee(@PathVariable Long id)
    {
        return ResponseEntity.ok(employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    //Update single employee , next time try to use email for updating
    @PutMapping("/employees/{id}")
    ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

            employee.setFirstName(newEmployee.getFirstName());
            employee.setLastName(newEmployee.getLastName());
            employee.setEmail(newEmployee.getEmail());

            Employee updatedEmployee = employeeRepository.save(employee);

            return ResponseEntity.ok(updatedEmployee);
    }

    //Delete Employee
    @DeleteMapping("/employees/{id}")
    ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id)
    {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
