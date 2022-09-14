package com.animesh245.backend.repository;

import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Employee findEmployeeByFullName(String fullName);
    Employee findEmployeeByRole(Role role);

    Employee findEmployeeByEmail(String email);

}
