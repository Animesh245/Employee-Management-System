package com.animesh245.backend.controller.definition;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/public")
public interface AppController
{
    @GetMapping(value = "/")
    String hello();

}
