/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.PistaDto;
import org.una.UNAeropuerto.entities.Pista;
import org.una.UNAeropuerto.repositories.IPistaRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class PistaServiceImplementation implements IPistaService {

    @Autowired
    private IPistaRepository pistaRepo;

    @Override
    @Transactional(readOnly = true)
    public PistaDto getById(long id) {
        Optional<Pista> result = pistaRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), PistaDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PistaDto getByNumeroPista(String numeroPista) {
        Optional<Pista> result = pistaRepo.findByNumeroPista(numeroPista);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), PistaDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PistaDto> findByNumeroPista(String numeroPista) {
        List<Pista> result = pistaRepo.findByNumeroPistaContaining(numeroPista);
        if (!result.isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result, PistaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PistaDto> findByEstado(boolean estate) {
        Optional<List<Pista>> result = pistaRepo.findByActivo(estate);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), PistaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public PistaDto create(PistaDto pista) {
        Pista entityPista = MapperUtils.entityFromDto(pista, Pista.class);
        entityPista = pistaRepo.save(entityPista);
        return MapperUtils.DtoFromEntity(entityPista, PistaDto.class);
    }

    @Override
    @Transactional
    public PistaDto update(PistaDto pista) {
        Optional<Pista> result = pistaRepo.findById(pista.getId());
        if (result.isPresent()) {
            Pista entity = MapperUtils.entityFromDto(pista, Pista.class);
            entity = pistaRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, PistaDto.class);
        }
        return null;
    }

    @Override
    public List<PistaDto> findAll() {
        List<Pista> result = pistaRepo.findAll();
        if (!result.isEmpty()) {
            result.removeIf(act -> !act.getActivo());
            return MapperUtils.DtoListFromEntityList(result, PistaDto.class);
        }
        return new ArrayList();
    }

    @Override
    @SuppressWarnings("null")
    public List<PistaDto> filter(String numerPista, float longitud) {
        List<Pista> resultS = null;
        List<Pista> resultF = new ArrayList();
        resultS = pistaRepo.findByNumeroPistaContaining(numerPista);
        if (-1.0 != longitud) {
            resultF = pistaRepo.findLongitudInRange(longitud, 1000);
        }
        if (!resultS.isEmpty() || !resultF.isEmpty()) {
            ArrayList<Pista> conbinedList = new ArrayList();
            conbinedList.addAll(resultF);
            conbinedList.addAll(resultS);
            List<Pista> finaList = conbinedList.stream().distinct().collect(Collectors.toList());
            finaList.removeIf(act -> !act.getActivo());
            List<PistaDto> dtoList = MapperUtils.DtoListFromEntityList(finaList, PistaDto.class);
            return dtoList;
        }
        return new ArrayList<>();
    }

}
