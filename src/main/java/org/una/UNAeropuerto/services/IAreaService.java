/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.AreaDto;

/**
 *
 * @author Roberth :)
 */
public interface IAreaService {

    public AreaDto getById(long id);

    public AreaDto getByNombre(String nombre);

    public List<AreaDto> findByNombre(String nombre);

    public List<AreaDto> findByDescripcion(String descripcion);
}
