package com.animesh245.backend.controller.definition;

import com.animesh245.backend.dtos.request.RequestProject;
import com.animesh245.backend.dtos.response.ResponseProject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/v1/projects")
public interface ProjectController
{
    @GetMapping("/")
    List<ResponseProject> getAllProjects();

    @PostMapping("/")
    void newProject(@ModelAttribute RequestProject requestProject);

    @GetMapping("/{id}")
    ResponseEntity<ResponseProject> getProject(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<String > replaceProject( @PathVariable String id, @ModelAttribute RequestProject requestProject);

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> deleteProject(@PathVariable String  id);
}
