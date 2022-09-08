package com.animesh245.backend.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDependent
{
    private Long id;
    private String dependentName;
    private String  gender;
    private String  relationship;
    private String  employeeName;
}
