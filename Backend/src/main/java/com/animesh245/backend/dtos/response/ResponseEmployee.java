package com.animesh245.backend.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseEmployee
{
    private String fullName;
    private String address;
    private String email;
    private String dateOfBirth;
    private String workSchedule;
    private String dateOfJoin;
    private String worksIn;
    private List<String > projectList;
    private List<String > dependentList;
    private String profilePhotoPath;
}
