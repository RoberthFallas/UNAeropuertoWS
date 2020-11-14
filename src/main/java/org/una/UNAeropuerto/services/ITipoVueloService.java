/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.TipoVueloDto;
import java.util.List;

/**
 *
 * @author roberth
 */
public interface ITipoVueloService {

    public List<TipoVueloDto> findAll();
    
    public List<TipoVueloDto> findByEstado(boolean estado);
}
