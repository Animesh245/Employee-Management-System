package com.animesh245.backend.controller.definition;

import com.animesh245.backend.dtos.request.RequestDepartment;
import com.animesh245.backend.dtos.response.ResponseDepartment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/v1/departments")
public interface DepartmentController
{
    @GetMapping("/")
    List<ResponseDepartment> getAllDepartments();

    @PostMapping("/")
    void newDepartment(@ModelAttribute RequestDepartment requestDepartment);

    @GetMapping("/{id}")
    ResponseEntity<ResponseDepartment> getDepartment(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<String > replaceDepartment( @PathVariable String id, @ModelAttribute RequestDepartment requestDepartment);

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> deleteDepartment(@PathVariable String  id);
}
