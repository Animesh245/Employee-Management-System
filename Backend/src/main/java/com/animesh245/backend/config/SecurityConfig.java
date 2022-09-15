package com.animesh245.backend.config;

import com.animesh245.backend.service.definition.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    private final PasswordEncoder passwordEncoder;
    private final EmployeeService employeeService;
    private final AuthFilter authFilter;

    public SecurityConfig(PasswordEncoder passwordEncoder, EmployeeService employeeService, AuthFilter authFilter)
    {
        this.passwordEncoder = passwordEncoder;
        this.employeeService = employeeService;
        this.authFilter = authFilter;
    }



    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(employeeService).passwordEncoder(passwordEncoder);
    }



    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/api/v1/employees/**", "/api/v1/projects/**", "/api/v1/departments/**", "/api/v1/dependents/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers( HttpMethod.GET,"/api/v1/dependents/**", "/api/v1/employees/{id}").hasAuthority("ROLE_EMPLOYEE")
//                .antMatchers(HttpMethod.POST, "/api/v1/employees/{id}", "/api/v1/employees/**").hasAuthority("ROLE_EMPLOYEE")
//                .antMatchers("/api/v1/employees/**","/api/v1/projects/**", "/api/v1/departments/**","/api/v1/dependents/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();

        return http.build();
    }
}
