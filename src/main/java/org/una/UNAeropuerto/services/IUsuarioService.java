/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.UsuarioDto;

/**
 *
 * @author Roberth :)
 */
public interface IUsuarioService {

    public UsuarioDto getById(long id);

    public UsuarioDto getByCedula(String cedula);

    public UsuarioDto findByCedulaAproximada(String parameter);

    public UsuarioDto findByNameAndApellidos(String nombre, String apellidos);

    public UsuarioDto update(UsuarioDto usuario);

    public UsuarioDto create(UsuarioDto usuario);

    public UsuarioDto ocultarById(long id);

}
