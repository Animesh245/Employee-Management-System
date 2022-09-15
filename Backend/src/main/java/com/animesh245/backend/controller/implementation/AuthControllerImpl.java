package com.animesh245.backend.controller.implementation;

import com.animesh245.backend.controller.definition.AuthController;
import com.animesh245.backend.dtos.request.JwtRequest;
import com.animesh245.backend.dtos.response.JwtResponse;
import com.animesh245.backend.service.definition.AuthService;
import com.animesh245.backend.service.definition.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController
{
    private final EmployeeService employeeService;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthControllerImpl(EmployeeService employeeService, AuthService authService, AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login()
    {
        return "Login page";
    }

    @Override
    public String logout()
    {
        return "Logout page";
    }

    @Override
    public ResponseEntity<?> generateToken(JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.employeeService.loadUserByUsername(jwtRequest.getUsername());

        String token = this.authService.generateToken(userDetails);
        System.out.println("JWT" + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
