package com.animesh245.backend.service.implementation;

import com.animesh245.backend.dtos.request.RequestProject;
import com.animesh245.backend.dtos.response.ResponseProject;
import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.entity.Project;
import com.animesh245.backend.exception.NotFoundException;
import com.animesh245.backend.repository.ProjectRepository;
import com.animesh245.backend.service.definition.DepartmentService;
import com.animesh245.backend.service.definition.EmployeeService;
import com.animesh245.backend.service.definition.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService
{
    private final ProjectRepository projectRepository;
    private final EmployeeService   employeeService;
    private final DepartmentService departmentService;

    public ProjectServiceImpl(ProjectRepository projectRepository, @Lazy EmployeeService employeeService,@Lazy DepartmentService departmentService)
    {
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public List<Project> findByEmployee(String employeeName)
    {
        return projectRepository.findEmployeesByProject(projectName);
    }

    @Override
    public Set<Project> findProjectsByDepartment(String departmentName)
    {
        Department department = departmentService.findDepartmentByName(departmentName);
        return projectRepository.findProjectsByDepartment(department);
    }

    @Override
    public Project findByName(String projectName)
    {
        return projectRepository.findProjectByProjectName(projectName);
    }

    @Override
    public void saveProject(RequestProject requestProject)
    {
        var project = dtoToEntity(requestProject);
        projectRepository.save(project);
    }

    @Override
    public ResponseProject getProject(String id)
    {
        var project = projectRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException(id));
        return entityToDto(project);
    }

    @Override
    public List<ResponseProject> getProjectList()
    {
        List<ResponseProject> responseProjectList = new ArrayList<>();
        List<Project> projectList = projectRepository.findAll();
        for (Project project: projectList)
        {
            var responseProject = entityToDto(project);
            responseProjectList.add(responseProject);
        }
        return responseProjectList;
    }

    @Override
    public void updateProject(String id, RequestProject requestProject)
    {
        var project = projectRepository.findById(Long.parseLong(id)).orElseThrow(()-> new NotFoundException(id));
        var projectUpdated = dtoToEntity(requestProject);
        projectUpdated.setId(project.getId());
        projectRepository.save(projectUpdated);
    }

    @Override
    public void deleteProject(String id)
    {
        projectRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public Project dtoToEntity(RequestProject requestProject)
    {
        var department = departmentService.findDepartmentByName(requestProject.getDepartment());
        var employeeList = employeeService.findEmployeesByProject(requestProject.getProjectName());

        var project = new Project();
        BeanUtils.copyProperties(requestProject,project);
        project.setDepartment(department);
        project.setEmployees(employeeList);
        return project;
    }

    @Override
    public ResponseProject entityToDto(Project project)
    {
        var employeeList = employeeService.findEmployeesByProject(project.getProjectName());
        var employeeNameList = new ArrayList<String>();

        for (Employee employee: employeeList)
        {
            var employeeName = employeeService.entityToDto(employee);
            employeeNameList.add(employeeName.getFullName());
        }

        var responseProject = new ResponseProject();
        BeanUtils.copyProperties(project, responseProject);
        responseProject.setDepartment(project.getDepartment().toString());
        responseProject.setEmployeeList(employeeNameList);
        return responseProject;
    }
}
