package com.animesh245.backend.controller.implementation;

import com.animesh245.backend.controller.definition.DependentController;
import com.animesh245.backend.dtos.request.RequestDependent;
import com.animesh245.backend.dtos.response.ResponseDependent;
import com.animesh245.backend.service.definition.DependentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DependentControllerImpl implements DependentController
{
    private final DependentService dependentService;

    public DependentControllerImpl(DependentService dependentService) {
        this.dependentService = dependentService;
    }

    @Override
    public List<ResponseDependent> getAllDependents()
    {
        return dependentService.getDependents();
    }

    @Override
    public void newDependent(RequestDependent requestDependent)
    {
        dependentService.saveDependent(requestDependent);
    }

    @Override
    public ResponseEntity<ResponseDependent> getDependent(String id)
    {
        return ResponseEntity.ok(dependentService.getDependent(id));
    }

    @Override
    public ResponseEntity<String> replaceDependent(String id, RequestDependent requestDependent)
    {
        dependentService.updateDependent(id, requestDependent);
        return ResponseEntity.ok(id+ "dependent is updated");
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteDependent(String id)
    {
        dependentService.deleteDependent(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
