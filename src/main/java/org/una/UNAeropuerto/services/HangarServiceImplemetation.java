package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.HangarDto;
import org.una.UNAeropuerto.entities.Hangar;
import org.una.UNAeropuerto.repositories.IHangarRepository;
import org.una.UNAeropuerto.utils.MapperUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class HangarServiceImplemetation implements  IHangarService{
    @Autowired
    IHangarRepository hangarRepository;

    @Override
    @Transactional(readOnly = true)
    public HangarDto getById(long id) {
        Optional<Hangar> result = hangarRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), HangarDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HangarDto> findByEstado(boolean estado) {
        Optional<List<Hangar>> result = hangarRepository.findByActivoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), HangarDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HangarDto> findByEspecialidadAproximado(String parametro) {
        Optional<List<Hangar>> result = hangarRepository.findByEspecialidadContaining(parametro);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), HangarDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public HangarDto update(HangarDto hangar) {
        Optional<Hangar> result = hangarRepository.findById(hangar.getId());
        if (result.isPresent()) {
            Hangar entity = MapperUtils.entityFromDto(hangar, Hangar.class);
            entity = hangarRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, HangarDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public HangarDto create(HangarDto hangar) {
        Hangar entityUser = MapperUtils.entityFromDto(hangar, Hangar.class);
        entityUser = hangarRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, HangarDto.class);
    }
}
