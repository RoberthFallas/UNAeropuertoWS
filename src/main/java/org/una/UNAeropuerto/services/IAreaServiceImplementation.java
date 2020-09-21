/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.UNAeropuerto.dto.AreaDto;
import org.una.UNAeropuerto.entities.Area;
import org.una.UNAeropuerto.repositories.IAreaRepository;
import org.una.UNAeropuerto.services.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class IAreaServiceImplementation implements IAreaService {

    @Autowired
    IAreaRepository areaRepo;

    @Override
    public AreaDto getById(long id) {
        Optional<Area> result = areaRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), AreaDto.class);
        }
        return null;
    }

    @Override
    public AreaDto getByNombre(String nombre) {
        Optional<Area> result = areaRepo.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), AreaDto.class);
        }
        return null;
    }

    @Override
    public List<AreaDto> findByNombre(String nombre) {
        Optional<List<Area>> result = areaRepo.findByNombreContaining(nombre);
        if (!result.get().isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result.get(), AreaDto.class);
        }
        return new ArrayList();
    }

    @Override
    public List<AreaDto> findByDescripcion(String descripcion) {
        Optional<List<Area>> result = areaRepo.findByDescripcionContaining(descripcion);
        if (!result.get().isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result.get(), AreaDto.class);
        }
        return new ArrayList();
    }

    @Override
    public AreaDto update(AreaDto area) {
        Optional<Area> result = areaRepo.findById(area.getId());
        if (result.isPresent()) {
            Area entity = MapperUtils.entityFromDto(area, Area.class);
            entity = areaRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, AreaDto.class);
        }
        return null;
    }

    @Override
    public AreaDto create(AreaDto usuario) {
        Area entityUser = MapperUtils.entityFromDto(usuario, Area.class);
        entityUser = areaRepo.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, AreaDto.class);
    }

}
