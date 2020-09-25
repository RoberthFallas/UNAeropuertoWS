/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.DireccionDto;
import org.una.UNAeropuerto.entities.Direccion;
import org.una.UNAeropuerto.repositories.IDireccionRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class DireccionServiceImplementation implements IDireccionService {

    @Autowired
    private IDireccionRepository direcRepo;

    @Override
    @Transactional(readOnly = true)
    public List<DireccionDto> findByDireccionVuelo(String direcVuelo) {
        Optional<List<Direccion>> result = direcRepo.findByDireccionVuelo(direcVuelo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), DireccionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DireccionDto> findByLugarId(long id) {
        Optional<List<Direccion>> result = direcRepo.findByLugaresIdId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), DireccionDto.class);
        }
        return new ArrayList();
    }

}
