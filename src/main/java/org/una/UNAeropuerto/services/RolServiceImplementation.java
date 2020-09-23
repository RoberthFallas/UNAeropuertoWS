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
import org.una.UNAeropuerto.dto.RolDto;
import org.una.UNAeropuerto.entities.Rol;
import org.una.UNAeropuerto.repositories.IRolRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */

@Service
public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository rolRepo;

    @Override
    @Transactional(readOnly = true)
    public RolDto getById(long id) {
        Optional<Rol> result = rolRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), RolDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public RolDto getByNombre(String nombre) {
        Optional<Rol> result = rolRepo.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), RolDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolDto> findByNombre(String nombre) {
        Optional<List<Rol>> result = rolRepo.findByNombreContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), RolDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolDto> findByDescripcion(String descripcion) {
        Optional<List<Rol>> result = rolRepo.findByDescripcionContaining(descripcion);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), RolDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolDto> findByestado(boolean esatdo) {
        Optional<List<Rol>> result = rolRepo.findByActivoLike(esatdo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), RolDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public RolDto create(RolDto rol) {
        Rol entityRol = MapperUtils.entityFromDto(rol, Rol.class);
        entityRol = rolRepo.save(entityRol);
        return MapperUtils.DtoFromEntity(entityRol, RolDto.class);
    }

    @Override
    @Transactional
    public RolDto update(RolDto rol) {
        Optional<Rol> result = rolRepo.findById(rol.getId());
        if (result.isPresent()) {
            Rol entity = MapperUtils.entityFromDto(rol, Rol.class);
            entity = rolRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, RolDto.class);
        }
        return null;
    }

}
