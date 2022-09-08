package com.animesh245.backend.service.definition;

import com.animesh245.backend.dtos.request.RequestProject;
import com.animesh245.backend.dtos.response.ResponseProject;
import com.animesh245.backend.entity.Project;

import java.util.List;
import java.util.Set;

public interface ProjectService
{
    List<Employee> findEmployeesByProject(String projectName);


//    List<Project> findByEmployee(String employeeName);

Set<Project> findProjectsByDepartment(String departmentName);
    Project findByName(String projectName);

    void saveProject(RequestProject requestProject);

    ResponseProject getProject(String id);

    List<ResponseProject> getProjectList();

    void updateProject(String id, RequestProject requestProject);

    void deleteProject(String id);

    Project dtoToEntity(RequestProject requestProject);

    ResponseProject entityToDto(Project project);
}
