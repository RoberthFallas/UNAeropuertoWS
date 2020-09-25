package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.AvionDto;
import org.una.UNAeropuerto.entities.Avion;
import org.una.UNAeropuerto.repositories.IAvionRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvionServiceImplementation implements IAvionService{
    
    @Autowired
    IAvionRepository avionRepository;
    @Override
    @Transactional(readOnly = true)
    public AvionDto getById(long id) {
        Optional<Avion> result = avionRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), AvionDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public AvionDto getByMatricula(String matricula) {
        Optional<Avion> result = avionRepository.findByMatricula(matricula);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), AvionDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvionDto> findByEstado(boolean estado) {
        Optional<List<Avion>> result = avionRepository.findByActivoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), AvionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvionDto> findByAerolineaId(long id) {
        Optional<List<Avion>> result = avionRepository.findByAerolineasId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), AvionDto.class);
        }
        return new ArrayList();
    }



    @Override
    @Transactional
    public AvionDto update(AvionDto avion) {
        Optional<Avion> result = avionRepository.findById(avion.getId());
        if (result.isPresent()) {
            Avion entity = MapperUtils.entityFromDto(avion, Avion.class);
            entity = avionRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, AvionDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public AvionDto create(AvionDto avion) {
        Avion entityUser = MapperUtils.entityFromDto(avion, Avion.class);
        entityUser = avionRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, AvionDto.class);
    }
}
