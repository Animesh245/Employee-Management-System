package com.animesh245.backend.repository;

import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{
    Project findProjectByProjectName(String projectName);

    List<Project> findProjectsByDepartment(String departmentName); // needs correction

    List<Project> findProjectsByEmployeesContaining(Employee employee);
}
