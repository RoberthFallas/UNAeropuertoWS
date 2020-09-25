/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.DireccionDto;

/**
 *
 * @author Roberth :)
 */
public interface IDireccionService {

    public List<DireccionDto> findByDireccionVuelo(String direcVuelo);

    public List<DireccionDto> findByLugarId(long id);

}
