package com.animesh245.backend.repository;

import com.animesh245.backend.entity.Department;
import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{
    Project findProjectByProjectName(String projectName);

    @Query(value = "SELECT p.employees FROM Project p WHERE p.projectName= ?1")
    List<Employee> findEmployeesByProject(String projectName);

    Set<Project> findProjectsByDepartment(Department department);
}
