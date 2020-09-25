/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.AuthenticationRequest;
import org.una.UNAeropuerto.dto.UsuarioDto;
import org.una.UNAeropuerto.entities.Usuario;

/**
 *
 * @author Roberth :)
 */
public interface IUsuarioService {

    public UsuarioDto getById(long id);

    public UsuarioDto getByCedula(String cedula);

    public List<UsuarioDto> findByCedulaAproximada(String parameter);

    public List<UsuarioDto> findByNombreAndApellidos(String nombre, String apellidos);

    public UsuarioDto update(UsuarioDto usuario);

    public UsuarioDto create(UsuarioDto usuario);

    public UsuarioDto ocultarById(long id);
    // public Optional<ClienteDTO> findByCedula(String cedula);

}
