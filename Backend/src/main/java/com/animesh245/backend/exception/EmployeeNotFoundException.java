package com.animesh245.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException
{
    public EmployeeNotFoundException(Long id)
    {
        super("Could not find Employee " + id);
    }
}
