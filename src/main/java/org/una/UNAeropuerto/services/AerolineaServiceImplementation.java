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
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.AerolineaDto;
import org.una.UNAeropuerto.entities.Aerolinea;
import org.una.UNAeropuerto.repositories.IAerolineaRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class AerolineaServiceImplementation implements IAerolineaService {

    @Autowired
    private IAerolineaRepository aeroRepo;

    @Override
    @Transactional(readOnly = true)
    public AerolineaDto getById(long id) {
        Optional<Aerolinea> result = aeroRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), AerolineaDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public AerolineaDto getByNombre(String nombre) {
        Optional<Aerolinea> result = aeroRepo.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), AerolineaDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AerolineaDto> findByNombre(String nombre) {
        List<Aerolinea> result = aeroRepo.findByNombreContaining(nombre);
        if (!result.isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result, AerolineaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AerolineaDto> findByEstado(boolean estado) {
        List<Aerolinea> result = aeroRepo.findByActiov(estado);
        if (!result.isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result, AerolineaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public AerolineaDto create(AerolineaDto aerolinea) {
        Aerolinea entityAero = MapperUtils.entityFromDto(aerolinea, Aerolinea.class);
        entityAero = aeroRepo.save(entityAero);
        return MapperUtils.DtoFromEntity(entityAero, AerolineaDto.class);
    }

    @Override
    @Transactional
    public AerolineaDto update(AerolineaDto aerolinea) {
        Optional<Aerolinea> result = aeroRepo.findById(aerolinea.getId());
        if (result.isPresent()) {
            Aerolinea entity = MapperUtils.entityFromDto(aerolinea, Aerolinea.class);
            entity = aeroRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, AerolineaDto.class);
        }
        return null;
    }

}
