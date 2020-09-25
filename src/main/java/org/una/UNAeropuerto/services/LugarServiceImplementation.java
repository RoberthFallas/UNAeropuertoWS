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
import org.una.UNAeropuerto.dto.LugarDto;
import org.una.UNAeropuerto.entities.Lugar;
import org.una.UNAeropuerto.repositories.ILugarRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class LugarServiceImplementation implements ILugarService {

    @Autowired
    private ILugarRepository lugarRepo;

    @Override
    @Transactional(readOnly = true)
    public LugarDto getById(long id) {
        Optional<Lugar> result = lugarRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), LugarDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public LugarDto getByNombre(String nombre) {
        Optional<Lugar> result = lugarRepo.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), LugarDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LugarDto> findByNombre(String nombre) {
        Optional<List<Lugar>> result = lugarRepo.findByNombreContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), LugarDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LugarDto> findByEstado(boolean estate) {
        Optional<List<Lugar>> result = lugarRepo.findByActivo(estate);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), LugarDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public LugarDto create(LugarDto lugar) {
        Lugar entityLugar = MapperUtils.entityFromDto(lugar, Lugar.class);
        entityLugar = lugarRepo.save(entityLugar);
        return MapperUtils.DtoFromEntity(entityLugar, LugarDto.class);
    }

    @Override
    @Transactional
    public LugarDto update(LugarDto lugar) {
        Optional<Lugar> result = lugarRepo.findById(lugar.getId());
        if (result.isPresent()) {
            Lugar entity = MapperUtils.entityFromDto(lugar, Lugar.class);
            entity = lugarRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, LugarDto.class);
        }
        return null;
    }

}
