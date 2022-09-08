package com.animesh245.backend.controller.definition;

import com.animesh245.backend.dtos.request.RequestEmployee;
import com.animesh245.backend.dtos.response.ResponseEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")// when modules are run separately
@RequestMapping(value = "/api/v1/employees")
public interface EmployeeController
{
    @GetMapping("/")
    List<ResponseEmployee> getAllEmployees();

    @PostMapping("/")
    void newEmployee(@ModelAttribute RequestEmployee requestEmployee);

    @GetMapping("/{id}")
    ResponseEntity<ResponseEmployee> getEmployee(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<String > replaceEmployee( @PathVariable String id, @ModelAttribute RequestEmployee requestEmployee);

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String  id);
}
