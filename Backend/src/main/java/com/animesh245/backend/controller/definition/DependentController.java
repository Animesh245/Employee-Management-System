package com.animesh245.backend.controller.definition;

import com.animesh245.backend.dtos.request.RequestDependent;
import com.animesh245.backend.dtos.response.ResponseDependent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")// when modules are run separately
@RequestMapping(value = "/api/v1/dependents")
public interface DependentController
{
    @GetMapping("/")
    List<ResponseDependent> getAllDependents();

    @PostMapping("/")
    void newDependent(@ModelAttribute RequestDependent requestDependent);

    @GetMapping("/{id}")
    ResponseEntity<ResponseDependent> getDependent(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<String > replaceDependent( @PathVariable String id, @ModelAttribute RequestDependent requestDependent);

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> deleteDependent(@PathVariable String  id);
}
