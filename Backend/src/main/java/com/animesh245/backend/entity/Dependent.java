package com.animesh245.backend.entity;

import com.animesh245.backend.enums.Gender;
import com.animesh245.backend.enums.Relationship;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "dependents")
public class Dependent
{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dependent_name")
    private String dependentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship")
    private Relationship relationship;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dependent_on", referencedColumnName = "id")
    private Employee employee;
}
