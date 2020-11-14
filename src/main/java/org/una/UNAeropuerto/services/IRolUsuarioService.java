/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.RolUsuarioDto;

/**
 *
 * @author Roberth :)
 */
public interface IRolUsuarioService {

    public List<RolUsuarioDto> findByUsuarioId(long id);

    public RolUsuarioDto create(RolUsuarioDto rolUsuario);

    public RolUsuarioDto update(RolUsuarioDto rolUsuario);

    public RolUsuarioDto changeState(long id, boolean state);
}
