package com.animesh245.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department
{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String deptName;

    @Column(name = "location")
    private String deptLocation;

    @Column(name = "manager_name")
    private String deptManager;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "worksIn",cascade = CascadeType.ALL)
    private Set<Employee> employeeList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private Set<Project> projects;
}
