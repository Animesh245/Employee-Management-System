package com.animesh245.backend.service.implementation;

import com.animesh245.backend.dtos.request.RequestDependent;
import com.animesh245.backend.dtos.response.ResponseDependent;
import com.animesh245.backend.entity.Dependent;
import com.animesh245.backend.enums.Gender;
import com.animesh245.backend.enums.Relationship;
import com.animesh245.backend.exception.NotFoundException;
import com.animesh245.backend.repository.DependentRepository;
import com.animesh245.backend.service.definition.DependentService;
import com.animesh245.backend.service.definition.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DependentServiceImpl implements DependentService
{
    private final EmployeeService employeeService;
    private final DependentRepository dependentRepository;

    @Override
    public void saveDependent(RequestDependent requestDependent)
    {
        var dependent = dtoToEntity(requestDependent);
        dependentRepository.save(dependent);
    }

    @Override
    public ResponseDependent getDependent(String id)
    {
        var dependent = dependentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException(id));
        return entityToDto(dependent);
    }

    @Override
    public List<ResponseDependent> getDependents()
    {
        var dependentList = dependentRepository.findAll();
        var responseDependentList = new ArrayList<ResponseDependent>();
        for (Dependent dependent: dependentList)
        {
            responseDependentList.add(entityToDto(dependent));
        }
        return responseDependentList;
    }

    @Override
    public void updateDependent(String id, RequestDependent requestDependent)
    {
        var dependent = dependentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException(id));
        var updatedDependent = new Dependent();
        updatedDependent.setId(dependent.getId());
        dependentRepository.save(updatedDependent);
    }

    @Override
    public void deleteDependent(String id)
    {
        dependentRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public Dependent dtoToEntity(RequestDependent requestDependent)
    {
        var dependent = new Dependent();
        BeanUtils.copyProperties(requestDependent, dependent);
        dependent.setGender(Gender.valueOf(requestDependent.getGender()));
        dependent.setRelationship(Relationship.valueOf(requestDependent.getRelationship()));
        dependent.setEmployee(employeeService.findEmployeeByName(requestDependent.getEmployeeName()));
        return dependent;
    }

    @Override
    public ResponseDependent entityToDto(Dependent dependent)
    {
        var responseDependent = new ResponseDependent();
        BeanUtils.copyProperties(dependent, responseDependent);
        responseDependent.setDependentName(dependent.getDependentName());
        responseDependent.setRelationship(dependent.getRelationship().toString());
        responseDependent.setGender(dependent.getGender().toString());
        responseDependent.setEmployeeName(dependent.getEmployee().getFullName());
        return responseDependent;
    }
}
