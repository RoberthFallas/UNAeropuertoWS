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
import org.una.UNAeropuerto.dto.AlertaDto;
import org.una.UNAeropuerto.entities.Alerta;
import org.una.UNAeropuerto.repositories.IAlertaRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class AlertaServiceImplementation implements IAlertaService {

    @Autowired
    private IAlertaRepository alertRepo;

    @Override
    @Transactional(readOnly = true)
    public AlertaDto getById(long id) {
        Optional<Alerta> result = alertRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), AlertaDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertaDto> findByTipo(byte tipo) {
        Optional<List<Alerta>> result = alertRepo.findByTipo(tipo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), AlertaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertaDto> findByTitulo(String titulo) {
        Optional<List<Alerta>> result = alertRepo.findByTituloContaining(titulo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), AlertaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertaDto> findByEmisor(String nombre) {
        Optional<List<Alerta>> result = alertRepo.findByEmisorContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), AlertaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public AlertaDto create(AlertaDto alerta) {
        Alerta entityAlert = MapperUtils.entityFromDto(alerta, Alerta.class);
        entityAlert = alertRepo.save(entityAlert);
        return MapperUtils.DtoFromEntity(entityAlert, AlertaDto.class);
    }

    @Override
    @Transactional
    public AlertaDto update(AlertaDto alerta) {
        Optional<Alerta> result = alertRepo.findById(alerta.getId());
        if (result.isPresent()) {
            Alerta entity = MapperUtils.entityFromDto(alerta, Alerta.class);
            entity = alertRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, AlertaDto.class);
        }
        return null;
    }

}
