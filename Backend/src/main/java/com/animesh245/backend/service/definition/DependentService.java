package com.animesh245.backend.service.definition;

import com.animesh245.backend.dtos.request.RequestDependent;
import com.animesh245.backend.dtos.response.ResponseDependent;
import com.animesh245.backend.entity.Dependent;

import java.util.List;

public interface DependentService
{
    void saveDependent(RequestDependent requestDependent);

    ResponseDependent getDependent(String id);

    List<ResponseDependent> getDependents();

    void updateDependent(String id, RequestDependent requestDependent);

    void deleteDependent(String id);

    Dependent dtoToEntity(RequestDependent requestDependent);

    ResponseDependent entityToDto(Dependent dependent);
}
