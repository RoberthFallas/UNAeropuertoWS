/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UsuarioDto getByCedula(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDto findByCedulaAproximada(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDto findByNameAndApellidos(String nombre, String apellidos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDto update(UsuarioDto usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDto create(UsuarioDto usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDto ocultarById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
