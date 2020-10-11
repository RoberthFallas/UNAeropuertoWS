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
import org.una.UNAeropuerto.dto.VueloDto;
import org.una.UNAeropuerto.entities.Vuelo;
import org.una.UNAeropuerto.utils.MapperUtils;
import org.una.UNAeropuerto.repositories.IVueloRepository;

/**
 *
 * @author Roberth :)
 */
@Service
public class VueloServiceImplementation implements IVueloService {

    @Autowired
    private IVueloRepository vueloRepo;

    @Override
    @Transactional(readOnly = true)
    public VueloDto getById(long id) {
        Optional<Vuelo> result = vueloRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), VueloDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public VueloDto getByNombreVuelo(String nombre) {
        Optional<Vuelo> result = vueloRepo.findByNombreVuelo(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), VueloDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> findByEstado(byte estado) {
        Optional<List<Vuelo>> result = vueloRepo.findByEstado(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), VueloDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> findByNombre(String nombre) {
        Optional<List<Vuelo>> result = vueloRepo.findByNombreVueloContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), VueloDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> findEntreFechaYHora(Date start, Date end) {
        Optional<List<Vuelo>> result = vueloRepo.findBitweenHoraYFecha(start, end);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), VueloDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> findEntreFechas(Date start, Date end) {
        Optional<List<Vuelo>> result = vueloRepo.findByBetweenDates(start, end);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), VueloDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> findByFechaVuelo(Date fecha) {
        Optional<List<Vuelo>> result = vueloRepo.findByFechaVuelo(fecha);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), VueloDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public VueloDto create(VueloDto vuelo) {
        Vuelo entityVuelo = MapperUtils.entityFromDto(vuelo, Vuelo.class);
        entityVuelo = vueloRepo.save(entityVuelo);
        return MapperUtils.DtoFromEntity(entityVuelo, VueloDto.class);
    }

    @Override
    @Transactional
    public VueloDto update(VueloDto vuelo) {
        Optional<Vuelo> result = vueloRepo.findById(vuelo.getId());
        if (result.isPresent()) {
            Vuelo entity = MapperUtils.entityFromDto(vuelo, Vuelo.class);
            entity = vueloRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, VueloDto.class);
        }
        return null;
    }

}
