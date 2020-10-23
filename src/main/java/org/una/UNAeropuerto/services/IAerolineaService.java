/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.AerolineaDto;

/**
 *
 * @author Roberth :)
 */
public interface IAerolineaService {

    public AerolineaDto getById(long id);

    public AerolineaDto getByNombre(String nombre);

    public List<AerolineaDto> findByNombre(String nombre);

    public List<AerolineaDto> findByEstado(boolean estado);

    public AerolineaDto create(AerolineaDto aerolinea);

    public AerolineaDto update(AerolineaDto aerolinea);
    
    public Long getCountVuelos(long aerolineaId);
}
