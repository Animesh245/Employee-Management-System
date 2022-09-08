package com.animesh245.backend.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseProject
{
    private Long id;
    private String projectName;
    private String projectBrief;
//    private String workingOn;
    private String department;
    private List<String> employeeList;
}
