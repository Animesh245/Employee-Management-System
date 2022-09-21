package com.animesh245.backend.controller.implementation;

import com.animesh245.backend.controller.definition.DepartmentController;
import com.animesh245.backend.dtos.request.RequestDepartment;
import com.animesh245.backend.dtos.response.ResponseDepartment;
import com.animesh245.backend.service.definition.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class DepartmentControllerImpl implements DepartmentController
{
    private final DepartmentService departmentService;

    @Override
    public List<ResponseDepartment> getAllDepartments()
    {
        return departmentService.getDepartments();
    }

    @Override
    public void newDepartment(RequestDepartment requestDepartment)
    {
        departmentService.saveDepartment(requestDepartment);
    }

    @Override
    public ResponseEntity<ResponseDepartment> getDepartment(String id)
    {
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }

    @Override
    public ResponseEntity<String> replaceDepartment(String id, RequestDepartment requestDepartment)
    {
        departmentService.updateDepartment(id, requestDepartment);
        return ResponseEntity.ok("department"+ id +" is updated" );
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteDepartment(String id)
    {
        departmentService.deleteDepartment(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
