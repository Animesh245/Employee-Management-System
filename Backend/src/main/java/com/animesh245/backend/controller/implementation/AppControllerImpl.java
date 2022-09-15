package com.animesh245.backend.controller.implementation;

import com.animesh245.backend.controller.definition.AppController;
import com.animesh245.backend.dtos.request.RequestEmployee;
import com.animesh245.backend.entity.Employee;
import com.animesh245.backend.entity.Project;
import com.animesh245.backend.enums.WorkSchedule;
import com.animesh245.backend.service.definition.DepartmentService;
import com.animesh245.backend.service.definition.DependentService;
import com.animesh245.backend.service.definition.EmployeeService;
import com.animesh245.backend.service.definition.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class AppControllerImpl implements AppController
{
//    private final EmployeeService employeeService;
//    private final DepartmentService departmentService;
//    private final ProjectService projectService;
//    private final DependentService dependentService;
//
//    public AppControllerImpl(EmployeeService employeeService, DepartmentService departmentService, ProjectService projectService, DependentService dependentService) {
//        this.employeeService = employeeService;
//        this.departmentService = departmentService;
//        this.projectService = projectService;
//        this.dependentService = dependentService;
//    }

    //Hello
    public String hello()
    {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }

//    public void init()
//    {
//        List<Project> projectList = projectService.findByEmployee("Monica Geller");
//
//          var requestEmployeeDto = new RequestEmployee();
//          requestEmployeeDto.setFullName("Monica Geller");
//          requestEmployeeDto.setAddress("New York, USA");
//          requestEmployeeDto.setWorksIn("Human Resource");
//          requestEmployeeDto.setDateOfBirth("1990-06-12");
//          requestEmployeeDto.setWorkSchedule(WorkSchedule.FULL_TIME.toString());
//          requestEmployeeDto.setDateOfJoin("2021-09-01");
//          requestEmployeeDto.setProfilePhoto(null);
//          employeeService.saveEmployee(requestEmployeeDto);
//    }
}
