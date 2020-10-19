/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.UNAeropuerto.dto.RolDto;

/**
 *
 * @author Roberth :)
 */
public interface IRolService {

    public RolDto getById(long id);

    public RolDto getByNombre(String nombre);

    public List<RolDto> findByNombre(String nombre);

    public List<RolDto> findByestado(boolean esatdo);

    public RolDto create(RolDto rol);

    public RolDto update(RolDto rol);

    public Optional<List<RolDto>> findAll();

}
