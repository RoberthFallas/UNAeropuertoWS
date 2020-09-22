/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.RolDto;

/**
 *
 * @author rober
 */
public interface IRolService {

    public RolDto getById(long id);

    public RolDto getByNombre(String nombre);

    public RolDto findByDescripcion(String descripcion);

    public List<RolDto> findByestado(boolean esatdo);

    public RolDto create(long id);

    public RolDto update(long id);

}
