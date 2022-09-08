package com.animesh245.backend.repository;

import com.animesh245.backend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>
{
    Department findByDeptName(String deptName);
}
