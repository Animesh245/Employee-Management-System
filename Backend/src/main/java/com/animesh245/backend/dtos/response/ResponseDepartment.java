package com.animesh245.backend.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDepartment
{
    private String deptName;
    private String deptLocation;
    private String deptManager;
    private List<String > projectList;
}
