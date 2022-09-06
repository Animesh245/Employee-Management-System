package com.animesh245.backend.service.definition;

import com.animesh245.backend.dtos.request.RequestEmployee;
import com.animesh245.backend.dtos.response.ResponseEmployee;
import com.animesh245.backend.entity.Employee;

import java.util.List;

public interface EmployeeService
{
    List<ResponseEmployee> getEmployees();

    ResponseEmployee getEmployee(String id);

    List<Employee> findEmployeesByProject(String projectName);

    Employee findEmployeeByName(String  employeeName);

    void saveEmployee(RequestEmployee requestEmployee);

    void updateEmployee(String id, RequestEmployee requestEmployee);

    ResponseEmployee entityToDto(Employee employee);

    Employee dtoToEntity(RequestEmployee requestEmployee);
}
