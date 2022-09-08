package com.animesh245.backend.service.implementation;

import com.animesh245.backend.dtos.request.RequestEmployee;
import com.animesh245.backend.dtos.response.ResponseEmployee;
import com.animesh245.backend.entity.Dependent;
import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.entity.Project;
import com.animesh245.backend.enums.Role;
import com.animesh245.backend.enums.WorkSchedule;
import com.animesh245.backend.exception.NotFoundException;
import com.animesh245.backend.repository.EmployeeRepository;
import com.animesh245.backend.service.definition.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    private final FileService fileService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService, FileService fileService)
    {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.fileService = fileService;
    }

    @Override
    public List<ResponseEmployee> getEmployees()
    {
        List<ResponseEmployee> responseEmployeeList = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee: employeeList)
        {
            var responseEmployee = entityToDto(employee);
            responseEmployeeList.add(responseEmployee);
        }
        return responseEmployeeList;
    }

    @Override
    public ResponseEmployee getEmployee(String id)
    {
        return entityToDto(employeeRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException(id)));
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
    public Employee findByRole(String role)
    {
        return employeeRepository.findEmployeeByRole(Role.valueOf(role));
    }

    @Override
    public void saveEmployee(RequestEmployee requestEmployee)
    {
        employeeRepository.save(dtoToEntity(requestEmployee));
    }

    @Override
    public void updateEmployee(String id, RequestEmployee requestEmployee)
    {
        Employee employee = employeeRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException(id));
        Employee employeeUpdated = dtoToEntity(requestEmployee);
        employeeUpdated.setId(employee.getId());
        employeeRepository.save(employeeUpdated);
    }

    @Override
    public ResponseEmployee entityToDto(Employee employee)
    {
        List<Project> projectList = employee.getProjects();
        List<String> responseProjectList = new ArrayList<>();
        for (Project project: projectList)
        {
            responseProjectList.add(project.getProjectName());
        }

        List<Dependent> dependentList = employee.getDependents();
        List<String > responseDependentList = new ArrayList<>();
        for (Dependent dependent: dependentList)
        {
            responseDependentList.add(dependent.getDependentName());
        }

        var responseEmployee = new ResponseEmployee();
        BeanUtils.copyProperties(employee, responseEmployee);
        responseEmployee.setDateOfBirth(employee.getDateOfBirth().toString());
        responseEmployee.setDateOfJoin(employee.getDateOfJoin().toString());
        responseEmployee.setWorksIn(employee.getWorksIn().getDeptName());
        responseEmployee.setRole(employee.getRole().toString());
        responseEmployee.setProjectList(responseProjectList);
        responseEmployee.setDependentList(responseDependentList);

        return responseEmployee;
    }

    @Override
    public Employee dtoToEntity(RequestEmployee requestEmployee)
    {
        var employee = new Employee();
        BeanUtils.copyProperties(requestEmployee, employee);
        employee.setDateOfBirth(LocalDate.parse(requestEmployee.getDateOfBirth()));
        employee.setWorkSchedule(WorkSchedule.valueOf(requestEmployee.getWorkSchedule()));
        employee.setOnLeave(false);
        employee.setDateOfJoin(LocalDate.parse(requestEmployee.getDateOfJoin()));
        employee.setRole(Role.valueOf(requestEmployee.getRole()));

        return employee;
    }
}
