package com.animesh245.backend.service.implementation;

import com.animesh245.backend.dtos.request.RequestDepartment;
import com.animesh245.backend.dtos.response.ResponseDepartment;
import com.animesh245.backend.entity.Department;
import com.animesh245.backend.entity.Project;
import com.animesh245.backend.enums.Role;
import com.animesh245.backend.exception.NotFoundException;
import com.animesh245.backend.repository.DepartmentRepository;
import com.animesh245.backend.repository.ProjectRepository;
import com.animesh245.backend.service.definition.DepartmentService;
import com.animesh245.backend.service.definition.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService
{
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,@Lazy ProjectService projectService, @Lazy EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Department findDepartmentByName(String departmentName)
    {
        return departmentRepository.findDepartmentByDeptName(departmentName);
    }

    @Override
    public void saveDepartment(RequestDepartment requestDepartment)
    {
        var department = dtoToEntity(requestDepartment);
        departmentRepository.save(department);
    }

    @Override
    public ResponseDepartment getDepartment(String id)
    {
        var department = departmentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException(id));
        return entityToDto(department);
    }

    @Override
    public List<ResponseDepartment> getDepartments()
    {
        var departmentList = departmentRepository.findAll();
        var responseDepartmentList = new ArrayList<ResponseDepartment>();
        for (Department department: departmentList)
        {
            var responseDepartment = entityToDto(department);
            responseDepartmentList.add(responseDepartment);
        }
        return responseDepartmentList;
    }

    @Override
    public void updateDepartment(String id, RequestDepartment requestDepartment)
    {
        var department = departmentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException(id));
        var departmentUpdated = dtoToEntity(requestDepartment);
        departmentUpdated.setId(department.getId());
        departmentRepository.save(departmentUpdated);
    }

    @Override
    public void deleteDepartment(String id)
    {
        departmentRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public Department dtoToEntity(RequestDepartment requestDepartment)
    {
        String manager = employeeService.findEmployeeByName(requestDepartment.getDeptManager()).getFullName();

        var department = new Department();
        BeanUtils.copyProperties(requestDepartment, department);
        department.setDeptManager(manager);

        return department;
    }

    @Override
    public ResponseDepartment entityToDto(Department department)
    {
        Set<Project> projectList = department.getProjects();
        var projectNameList = new ArrayList<String>();
        for (Project project: projectList)
        {
            projectNameList.add(project.getProjectName());
        }

        String manager = new String();

        Set<Employee> employeeList = department.getEmployeeList();
        var employeeNameList = new ArrayList<String >();
        for (Employee employee: employeeList)
        {
            employeeNameList.add(employee.getFullName());
            if (employee.getRole() == Role.MANAGER)
            {
                manager = employee.getFullName();
            }
        }

        var responseDepartment = new ResponseDepartment();
        BeanUtils.copyProperties(department,responseDepartment);
        responseDepartment.setDeptManager(manager);
        responseDepartment.setProjectList(projectNameList);
        responseDepartment.setEmployeeList(employeeNameList);
        return responseDepartment;
    }
}
