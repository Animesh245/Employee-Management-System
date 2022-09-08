package com.animesh245.backend.repository;

import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Employee findEmployeeByFullName(String fullName);
    Employee findEmployeeByRole(Role role);

}
