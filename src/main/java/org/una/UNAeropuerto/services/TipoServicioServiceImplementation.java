package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.TipoServicioDto;
import org.una.UNAeropuerto.dto.TipoDto;
import org.una.UNAeropuerto.dto.TipoServicioDto;
import org.una.UNAeropuerto.entities.TipoServicio;
import org.una.UNAeropuerto.entities.Tipo;
import org.una.UNAeropuerto.entities.TipoServicio;
import org.una.UNAeropuerto.repositories.ITipoServicioRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioServiceImplementation implements  ITipoServicioService {

    @Autowired
    ITipoServicioRepository tipoServicioRepository;
    @Override
    @Transactional(readOnly = true)
    public TipoServicioDto getByNombre(String nombre) {
        Optional<TipoServicio> result = tipoServicioRepository.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), TipoServicioDto.class);
        }
        return null;
    }

    @Override
    public TipoServicioDto getById(long id) {
        Optional<TipoServicio> result = tipoServicioRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), TipoServicioDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicioDto> findByNombreAproximado(String nombre) {
        Optional<List<TipoServicio>> result = tipoServicioRepository.findByNombreContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), TipoServicioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicioDto> findByDescripcionAproximado(String descripcion) {
        Optional<List<TipoServicio>> result = tipoServicioRepository.findByDescripcionContaining(descripcion);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), TipoServicioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicioDto> findByEstado(boolean estado) {
        Optional<List<TipoServicio>> result = tipoServicioRepository.findByAvtivoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), TipoServicioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public TipoServicioDto update(TipoServicioDto tipoServicioDto) {
        Optional<TipoServicio> result = tipoServicioRepository.findById(tipoServicioDto.getId());
        if (result.isPresent()) {
            TipoServicio entity = MapperUtils.entityFromDto(tipoServicioDto, TipoServicio.class);
            entity = tipoServicioRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, TipoServicioDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public TipoServicioDto create(TipoServicioDto tipoServicioDto) {
        TipoServicio entityUser = MapperUtils.entityFromDto(tipoServicioDto, TipoServicio.class);
        entityUser = tipoServicioRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, TipoServicioDto.class);
    }
}
