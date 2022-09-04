package com.animesh245.backend.repository;

import com.animesh245.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Employee findEmployeeByFullName(String fullName);

    List<Employee> findEmployeesByProjects(String projectName); // correction needs to be added
}
