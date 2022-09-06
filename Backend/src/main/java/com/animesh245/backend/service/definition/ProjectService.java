package com.animesh245.backend.service.definition;

import com.animesh245.backend.dtos.request.RequestProject;
import com.animesh245.backend.dtos.response.ResponseProject;
import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.entity.Project;

import java.util.List;

public interface ProjectService
{
    List<Project> findByEmployee(String employeeName);

//    Project findByName(String projectName);

    void saveProject(RequestProject requestProject);

    ResponseProject getProject(String id);

    List<ResponseProject> getProjectList();

    void updateProject(String id, RequestProject requestProject);

    void deleteProject(String id);

    Project dtoToEntity(RequestProject requestProject);

    ResponseProject entityToDto(Project project);
}
