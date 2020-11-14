package org.una.UNAeropuerto.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.ParamSistemaDto;
import org.una.UNAeropuerto.repositories.IParamSistemaRepository;
import org.una.UNAeropuerto.utils.MapperUtils;
import org.una.UNAeropuerto.entities.ParamSistema;

@Service
public class ParamSistemaServiceImplementation implements IParamSistemaService {

    @Autowired
    IParamSistemaRepository paramSistemaRepo;
//

    private Optional<ParamSistemaDto> oneToDto(Optional<ParamSistema> one) {
        if (one.isPresent()) {
            ParamSistemaDto paramSistemaDto = MapperUtils.DtoFromEntity(one.get(), ParamSistemaDto.class);
            return Optional.ofNullable(paramSistemaDto);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParamSistemaDto> getById(Integer id) {
        return oneToDto(paramSistemaRepo.findById(id));
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

    @Override
    @Transactional(readOnly = true)
    public Integer getSesionDurationMinutos() {
        Integer duration = paramSistemaRepo.getSesionDurationMinutos();
        return (duration != null) ? duration : 10;
    }

}
