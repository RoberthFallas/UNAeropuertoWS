package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.ParamSistemaDto;
import org.una.UNAeropuerto.dto.ParamSistemaDto;
import org.una.UNAeropuerto.entities.ParamSistema;
import org.una.UNAeropuerto.entities.ParamSistema;
import org.una.UNAeropuerto.repositories.IParamSistemaRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.util.Optional;
@Service
public class ParamSistemaServiceImplementation implements  IParamSistemaService {

    @Autowired
    IParamSistemaRepository paramSistemaRepo;


    @Override
    @Transactional(readOnly = true)
    public ParamSistemaDto getById(long id) {
        Optional<ParamSistema> result = paramSistemaRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), ParamSistemaDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public ParamSistemaDto create(ParamSistemaDto paramSistema) {
        ParamSistema entityParamSistema = MapperUtils.entityFromDto(paramSistema, ParamSistema.class);
        entityParamSistema = paramSistemaRepo.save(entityParamSistema);
        return MapperUtils.DtoFromEntity(entityParamSistema, ParamSistemaDto.class);
    }

    @Override
    @Transactional
    public ParamSistemaDto update(ParamSistemaDto paramSistema) {
        Optional<ParamSistema> result = paramSistemaRepo.findById(paramSistema.getId());
        if (result.isPresent()) {
            ParamSistema entity = MapperUtils.entityFromDto(paramSistema, ParamSistema.class);
            entity = paramSistemaRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, ParamSistemaDto.class);
        }
        return null;
    }

}
