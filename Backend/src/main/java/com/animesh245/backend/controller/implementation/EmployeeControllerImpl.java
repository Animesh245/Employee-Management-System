package com.animesh245.backend.controller.implementation;

import com.animesh245.backend.controller.definition.EmployeeController;
import com.animesh245.backend.dtos.request.RequestEmployee;
import com.animesh245.backend.dtos.response.ResponseEmployee;
import com.animesh245.backend.service.definition.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeControllerImpl implements EmployeeController
{
    private final EmployeeService employeeService;

    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Get All Employees
    public List<ResponseEmployee> getAllEmployees()
    {
        return employeeService.getEmployees();
    }

    //Add Employee
    public void newEmployee(RequestEmployee requestEmployee)
    {
        employeeService.saveEmployee(requestEmployee);
    }

    //Get Single Employee

    public ResponseEntity<ResponseEmployee> getEmployee(@PathVariable String  id)
    {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    //Update single employee , next time try to use email for updating

    public ResponseEntity<String > replaceEmployee(@PathVariable String  id, RequestEmployee requestEmployee)
    {

        employeeService.updateEmployee(id, requestEmployee);

//            return ResponseEntity.ok(updatedEmployee);
        return ResponseEntity.ok("Employee information updated");
    }

    //Delete Employee

    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String  id)
    {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//
//        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
