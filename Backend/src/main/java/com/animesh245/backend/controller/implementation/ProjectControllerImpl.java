package com.animesh245.backend.controller.implementation;

import com.animesh245.backend.controller.definition.ProjectController;
import com.animesh245.backend.dtos.request.RequestProject;
import com.animesh245.backend.dtos.response.ResponseProject;
import com.animesh245.backend.service.definition.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ProjectControllerImpl implements ProjectController
{
    private final ProjectService projectService;

    @Override
    public List<ResponseProject> getAllProjects()
    {
        return projectService.getProjectList();
    }

    @Override
    public void newProject(RequestProject requestProject)
    {
        projectService.saveProject(requestProject);
    }

    @Override
    public ResponseEntity<ResponseProject> getProject(String id)
    {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @Override
    public ResponseEntity<String> replaceProject(String id, RequestProject requestProject)
    {
        projectService.updateProject(id, requestProject);
        return ResponseEntity.ok(id + " project is updated");
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteProject(String id)
    {
        projectService.deleteProject(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
