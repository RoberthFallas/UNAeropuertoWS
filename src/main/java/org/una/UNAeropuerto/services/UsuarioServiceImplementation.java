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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.UsuarioDto;
import org.una.UNAeropuerto.entities.Usuario;
import org.una.UNAeropuerto.repositories.IUsuarioRepository;
import org.una.UNAeropuerto.services.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class UsuarioServiceImplementation implements IUsuarioService {

    @Autowired
    private IUsuarioRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getById(long id) {
        Optional<Usuario> result = userRepo.findById(id);
        if (result.isPresent()) {
            UsuarioDto dtoUser = MapperUtils.DtoFromEntity(result.get(), UsuarioDto.class);
            return dtoUser;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getByCedula(String cedula) {
        Optional<Usuario> result = userRepo.findByCedula(cedula);
        if (result.isPresent()) {
            UsuarioDto dtoUser = MapperUtils.DtoFromEntity(result.get(), UsuarioDto.class);
            return dtoUser;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> findByCedulaAproximada(String parameter) {
        Optional<List<Usuario>> result = userRepo.findByCedulaContaining(parameter);
        if (!result.get().isEmpty()) {
            List<UsuarioDto> dtoUserList = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDto.class);
            return dtoUserList;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> findByNombreAndApellidos(String nombre, String apellidos) {
        Optional<List<Usuario>> result = userRepo.findByNombreAndApellido(nombre, apellidos);
        if (!result.get().isEmpty()) {
            List<UsuarioDto> dtoUserList = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDto.class);
            return dtoUserList;
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public UsuarioDto update(UsuarioDto usuario) {
        Optional<Usuario> result = userRepo.findById(usuario.getId());
        if (result.isPresent()) {
            Usuario entity = MapperUtils.entityFromDto(usuario, Usuario.class);
            entity.refreshContrasenna(result.get().getContrasenna());
            encodePassword(entity, usuario);
            entity = userRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, UsuarioDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public UsuarioDto create(UsuarioDto usuario) {
        Usuario entityUser = MapperUtils.entityFromDto(usuario, Usuario.class);
        encodePassword(entityUser, usuario);
        entityUser = userRepo.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, UsuarioDto.class);
    }

    @Override
    @Transactional
    public UsuarioDto ocultarById(long id) {
        Optional<Usuario> result = userRepo.findById(id);
        if (result.isPresent()) {
            Usuario entityUser = result.get();
            entityUser.setActivo(false);
            entityUser = userRepo.save(entityUser);
            return MapperUtils.DtoFromEntity(entityUser, UsuarioDto.class);
        }
        return null;
    }

    private void encodePassword(Usuario user, UsuarioDto userDto) {
        if (!userDto.getContrasenna().isBlank()) {
            if (user.getContrasenna() == null) {
                user.refreshContrasenna(passwordEncoder.encode(userDto.getContrasenna()));
                return;
            } else {
                if (!user.getContrasenna().equals(userDto.getContrasenna())) {
                    if (!passwordEncoder.matches(userDto.getContrasenna(), user.getContrasenna())) {
                        user.refreshContrasenna(passwordEncoder.encode(userDto.getContrasenna()));
                    }
                }
                return;
            }
        }
        throw new IllegalArgumentException("Contraseña no es válida o se encuntra vacía (no se pude encriptar)");
    }
}
