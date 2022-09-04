package com.animesh245.backend.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RequestEmployee
{
    private String fullName;
    private String address;
    private String dateOfBirth;
    private String workSchedule;
    private String dateOfJoin;
    private String worksIn;
    private String email;
    private MultipartFile profilePhoto;
}
