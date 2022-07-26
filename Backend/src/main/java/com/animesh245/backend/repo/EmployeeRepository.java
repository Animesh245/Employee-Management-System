package com.animesh245.backend.repo;

import com.animesh245.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
}
