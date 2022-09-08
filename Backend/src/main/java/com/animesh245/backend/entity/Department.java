package com.animesh245.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "manager", referencedColumnName = "id")
    private Employee deptManager;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "worksIn",cascade = CascadeType.ALL)
    private Set<Employee> employeeList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private Set<Project> projects;
}
