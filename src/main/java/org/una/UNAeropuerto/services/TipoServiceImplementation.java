package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.TipoDto;
import org.una.UNAeropuerto.entities.Tipo;
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
    public TipoDto getById(long id) {
        Optional<Tipo> result = tipoRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), TipoDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoDto getByNombre(String nombre) {
        Optional<Tipo> result = tipoRepository.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), TipoDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoDto> findByNombre(String nombre) {
        Optional<List<Tipo>> result = tipoRepository.findByNombreContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), TipoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoDto> findByActivos(boolean activo) {
        Optional<List<Tipo>> result = tipoRepository.findByActivoLike(activo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), TipoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public TipoDto update(TipoDto Tipo) {
        Optional<Tipo> result = tipoRepository.findById(Tipo.getId());
        if (result.isPresent()) {
            Tipo entity = MapperUtils.entityFromDto(Tipo, Tipo.class);
            entity = tipoRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, TipoDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public TipoDto create(TipoDto usuario) {
        Tipo entityUser = MapperUtils.entityFromDto(usuario, Tipo.class);
        entityUser = tipoRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, TipoDto.class);
    }
}
