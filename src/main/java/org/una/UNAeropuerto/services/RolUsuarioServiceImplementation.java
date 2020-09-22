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
import org.una.UNAeropuerto.dto.RolUsuarioDto;
import org.una.UNAeropuerto.entities.RolUsuario;
import org.una.UNAeropuerto.repositories.IRolUsuarioRepository;
import org.una.UNAeropuerto.services.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class RolUsuarioServiceImplementation implements IRolUsuarioService {

    @Autowired
    private IRolUsuarioRepository rolUserRepo;

    @Override
    @Transactional(readOnly = true)
    public List<RolUsuarioDto> findByUsuarioId(long id) {
        Optional<List<RolUsuario>> result = rolUserRepo.findByusuariosIdId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), RolUsuarioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public RolUsuarioDto create(RolUsuarioDto rolUsuario) {
        RolUsuario entityRolUser = MapperUtils.entityFromDto(rolUsuario, RolUsuario.class);
        entityRolUser = rolUserRepo.save(entityRolUser);
        return MapperUtils.DtoFromEntity(entityRolUser, RolUsuarioDto.class);
    }

    @Override
    @Transactional
    public RolUsuarioDto changeState(long id, boolean state) {
        Optional<RolUsuario> result = rolUserRepo.findById(id);
        if (result.isPresent()) {
            RolUsuario entityRolUser = result.get();
            entityRolUser.setActivo(state);
            entityRolUser = rolUserRepo.save(entityRolUser);
            return MapperUtils.DtoFromEntity(entityRolUser, RolUsuarioDto.class);
        }
        return null;
    }

}
