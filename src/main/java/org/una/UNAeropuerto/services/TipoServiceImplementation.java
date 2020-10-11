package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.TipoReparacionDto;
import org.una.UNAeropuerto.entities.TipoReparacion;
import org.una.UNAeropuerto.repositories.ITipoRepository;
import org.una.UNAeropuerto.utils.MapperUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServiceImplementation implements ITipoService {

    @Autowired
    ITipoRepository tipoRepository;

    @Override
    public TipoReparacionDto getById(long id) {
        Optional<TipoReparacion> result = tipoRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), TipoReparacionDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoReparacionDto getByNombre(String nombre) {
        Optional<TipoReparacion> result = tipoRepository.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), TipoReparacionDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoReparacionDto> findByNombre(String nombre) {
        Optional<List<TipoReparacion>> result = tipoRepository.findByNombreContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), TipoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoReparacionDto> findByActivos(boolean activo) {
        Optional<List<TipoReparacion>> result = tipoRepository.findByActivoLike(activo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), TipoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public TipoReparacionDto update(TipoReparacionDto tipo) {
        Optional<TipoReparacion> result = tipoRepository.findById(tipo.getId());
        if (result.isPresent()) {
            TipoReparacion entity = MapperUtils.entityFromDto(tipo, TipoReparacion.class);
            entity = tipoRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, TipoReparacionDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public TipoReparacionDto create(TipoReparacionDto usuario) {
        TipoReparacion entityUser = MapperUtils.entityFromDto(usuario, TipoReparacion.class);
        entityUser = tipoRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, TipoReparacionDto.class);
    }
}
