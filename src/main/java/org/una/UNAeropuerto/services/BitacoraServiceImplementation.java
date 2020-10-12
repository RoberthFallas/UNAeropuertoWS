/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.BitacoraDto;
import org.una.UNAeropuerto.entities.Bitacora;
import org.una.UNAeropuerto.repositories.IBitacoraRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class BitacoraServiceImplementation implements IBitacoraService {

    @Autowired
    private IBitacoraRepository bitacoraRepo;

    @Override
    @Transactional(readOnly = true)
    public BitacoraDto getById(long id) {
        Optional<Bitacora> result = bitacoraRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), BitacoraDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findByUserId(long id) {
        Optional<List<Bitacora>> result = bitacoraRepo.findByusuariosIdId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findByFechaBitacora(Date fecha) {
        Optional<List<Bitacora>> result = bitacoraRepo.findByFechaModificacion(fecha);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findBetweenDates(Date startDate, Date endDate) {
        Optional<List<Bitacora>> result = bitacoraRepo.findBetweenDates(startDate, endDate);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public BitacoraDto create(BitacoraDto bitacora) {
        Bitacora entityBita = MapperUtils.entityFromDto(bitacora, Bitacora.class);
        entityBita = bitacoraRepo.save(entityBita);
        return MapperUtils.DtoFromEntity(entityBita, BitacoraDto.class);
    }

}
