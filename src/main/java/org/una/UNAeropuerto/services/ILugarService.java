/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.LugarDto;

/**
 *
 * @author Roberth :)
 */
public interface ILugarService {

    public LugarDto getById(long id);

    public LugarDto getByNombre(String nombre);

    public List<LugarDto> findByNombre(String nombre);

    public List<LugarDto> findByEstado(boolean estate);

    public LugarDto create(LugarDto lugar);

    public LugarDto update(LugarDto lugar);
    
    public List<LugarDto> findAllActiv();
}
