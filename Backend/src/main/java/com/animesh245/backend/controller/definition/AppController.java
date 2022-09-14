package com.animesh245.backend.controller.definition;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/public")
@CrossOrigin// when modules are run separately
public interface AppController
{
    @GetMapping(value = "/")
    String hello();

}
