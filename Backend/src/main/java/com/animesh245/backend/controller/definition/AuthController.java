package com.animesh245.backend.controller.definition;

import com.animesh245.backend.dtos.request.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/auth")
public interface AuthController
{
    @GetMapping(value = "/login")
    String login();

    @GetMapping(value = "/logout")
    String logout();

    @PostMapping("/token")
    ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception;

}
