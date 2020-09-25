package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.DetalleServicioDto;
import org.una.UNAeropuerto.entities.DetalleServicio;
import org.una.UNAeropuerto.repositories.IDetalleServicioRepository;
import org.una.UNAeropuerto.utils.MapperUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleServicioServiceImplementation implements IDetalleServicioService {
    @Autowired
    IDetalleServicioRepository detalleServicioRepository;

    @Override
    public DetalleServicioDto getById(long id) {
        Optional<DetalleServicio> result = detalleServicioRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), DetalleServicioDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleServicioDto> findByActivos(boolean activo) {
        Optional<List<DetalleServicio>> result = detalleServicioRepository.findByActivoLike(activo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), DetalleServicioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleServicioDto> findByTiposId(long id) {
        Optional<List<DetalleServicio>> result = detalleServicioRepository.findByTiposId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), DetalleServicioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public DetalleServicioDto update(DetalleServicioDto DetalleServicio) {
        Optional<DetalleServicio> result = detalleServicioRepository.findById(DetalleServicio.getId());
        if (result.isPresent()) {
            DetalleServicio entity = MapperUtils.entityFromDto(DetalleServicio, DetalleServicio.class);
            entity = detalleServicioRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, DetalleServicioDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public DetalleServicioDto create(DetalleServicioDto usuario) {
        DetalleServicio entityUser = MapperUtils.entityFromDto(usuario, DetalleServicio.class);
        entityUser = detalleServicioRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, DetalleServicioDto.class);
    }
}
