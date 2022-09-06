package com.animesh245.backend.entity;

import com.animesh245.backend.enums.WorkSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee
{

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_schedule")
    private WorkSchedule workSchedule;

    @Column(name = "start_time")
    private LocalDateTime projectStartTime;

    @Column(name = "end_time")
    private LocalDateTime projectEndTime;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "deptManager")
    private Department department; //department manager

    @JoinColumn(name = "current_project", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Project currentProject;

    @Column(name = "login_time")
    private LocalDateTime loginTime;

    @Column(name = "logout_time")
    private LocalDateTime logoutTime;

    @Column(name = "on_leave")
    private Boolean onLeave;

    @Column(name = "doj")
    private LocalDate dateOfJoin;

    @Column(name = "path")
    private String profilePhotoPath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "works_in", referencedColumnName = "id")
    private Department worksIn;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependent> dependents;
}
