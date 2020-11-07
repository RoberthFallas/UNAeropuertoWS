/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.TipoVueloDto;
import org.una.UNAeropuerto.entities.TipoVuelo;
import org.una.UNAeropuerto.repositories.ITipoVueloRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author roberth :)
 */
@Service
public class TipoVueloServiceImplementation implements ITipoVueloService {

    @Autowired
    private ITipoVueloRepository tipoVueloRepo;

    @Override
    @Transactional(readOnly = true)
    public List<TipoVueloDto> findAll() {
        List<TipoVuelo> result = tipoVueloRepo.findAll();
        if (!result.isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result, TipoVueloDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoVueloDto> findByEstado(boolean estado) {
        List<TipoVuelo> result = tipoVueloRepo.findByActivo(estado);
        if (!result.isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result, TipoVueloDto.class);
        }
        return new ArrayList();
    }

}
