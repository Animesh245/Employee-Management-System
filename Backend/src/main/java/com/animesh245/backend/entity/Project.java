package com.animesh245.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project
{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_brief", columnDefinition = "TEXT")
    private String projectBrief;

    @Column(name = "working_on")
    private Long workingOn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "projects")
    private Set<Employee> employees;
}
