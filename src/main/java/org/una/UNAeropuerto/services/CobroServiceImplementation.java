package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.CobroDto;
import org.una.UNAeropuerto.entities.Cobro;
import org.una.UNAeropuerto.repositories.ICobroRepository;
import org.una.UNAeropuerto.utils.MapperUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CobroServiceImplementation implements ICobroService {

    @Autowired
    ICobroRepository cobroRepository;

    @Override
    @Transactional(readOnly = true)
    public CobroDto getById(long id) {
        Optional<Cobro> result = cobroRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), CobroDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CobroDto> findByActivos(boolean estado) {
        Optional<List<Cobro>> result = cobroRepository.findByActivoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), CobroDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CobroDto> findByServiciosMantenimientoId(long id) {
        Optional<List<Cobro>> result = cobroRepository.findByServiciosMantenimientoId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), CobroDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CobroDto> findByMontoAproximado(long monto) {
        Optional<List<Cobro>> result = cobroRepository.findByMontoContaining(monto);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), CobroDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CobroDto> findByMontoBetween(long montoInicial, long montoFinal) {
        Optional<List<Cobro>> result = cobroRepository.findByMontoBetween(montoInicial,montoFinal);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), CobroDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CobroDto> findByDetalleCobroAproximado(String parametro) {
        Optional<List<Cobro>> result = cobroRepository.findByDetalleCobroContaining(parametro);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), CobroDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public CobroDto update(CobroDto cobro) {
        Optional<Cobro> result = cobroRepository.findById(cobro.getId());
        if (result.isPresent()) {
            Cobro entity = MapperUtils.entityFromDto(cobro, Cobro.class);
            entity = cobroRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, CobroDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public CobroDto create(CobroDto cobro) {
        Cobro entityUser = MapperUtils.entityFromDto(cobro, Cobro.class);
        entityUser = cobroRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, CobroDto.class);
    }
}
