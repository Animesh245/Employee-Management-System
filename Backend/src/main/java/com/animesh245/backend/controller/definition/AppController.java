package com.animesh245.backend.controller.definition;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "http://localhost:4200")// when modules are run separately
public interface AppController
{
    @GetMapping("/hello")
    String hello();

}
