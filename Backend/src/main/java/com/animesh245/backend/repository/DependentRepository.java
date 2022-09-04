package com.animesh245.backend.repository;

import com.animesh245.backend.entity.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long>
{
    Dependent findDependentByDependentName(String dependentName);
}
