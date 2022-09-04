package com.animesh245.backend.controller.implementation;

import com.animesh245.backend.controller.definition.AppController;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AppControllerImpl implements AppController
{
    //Hello
    public String hello()
    {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }
}
