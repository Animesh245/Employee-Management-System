package com.animesh245.backend.service.implementation;

import com.animesh245.backend.dtos.request.RequestEmployee;
import com.animesh245.backend.dtos.response.ResponseEmployee;
import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.enums.WorkSchedule;
import com.animesh245.backend.repository.EmployeeRepository;
import com.animesh245.backend.service.definition.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<ResponseEmployee> getEmployees()
    {
        return null;
    }

    @Override
    public ResponseEmployee getEmployee(String id)
    {
        return null;
    }

    @Override
    public List<Employee> findEmployeesByProject(String projectName)
    {
        return employeeRepository.findEmployeesByProjects(projectName);
    }

    @Override
    public Employee findEmployeeByName(String employeeName)
    {
        return employeeRepository.findEmployeeByFullName(employeeName);
    }

    @Override
    public Employee findEmployeeById(String id)
    {
        return null;
    }

    @Override
    public void saveEmployee(RequestEmployee requestEmployee)
    {

    }

    @Override
    public void updateEmployee(String id, RequestEmployee requestEmployee)
    {

    }

    @Override
    public ResponseEmployee entityToDto(Employee employee)
    {
        var responseEmployee = new ResponseEmployee();
        BeanUtils.copyProperties(employee, responseEmployee);

        return responseEmployee;
    }

    @Override
    public Employee dtoToEntity(RequestEmployee requestEmployee)
    {
        var employee = new Employee();
        BeanUtils.copyProperties(requestEmployee, employee);
        employee.setDateOfBirth(LocalDate.parse(requestEmployee.getDateOfBirth()));
        employee.setWorkSchedule(WorkSchedule.valueOf(requestEmployee.getWorkSchedule()));


        return employee;
    }
}
