package com.animesh245.backend.service.definition;

import com.animesh245.backend.dtos.request.RequestDepartment;
import com.animesh245.backend.dtos.response.ResponseDepartment;
import com.animesh245.backend.entity.Department;

import java.util.List;

public interface DepartmentService
{
    Department findDepartmentByName(String departmentName);
    void saveDepartment(RequestDepartment requestDepartment);

    ResponseDepartment getDepartment(String id);

    List<ResponseDepartment> getDepartments();

    void updateDepartment(String id, RequestDepartment requestDepartment);

    void deleteDepartment(String id);

    Department dtoToEntity(RequestDepartment requestDepartment);

    ResponseDepartment entityToDto(Department department);
}
