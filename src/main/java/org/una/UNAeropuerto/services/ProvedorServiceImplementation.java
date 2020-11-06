package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.ProvedorDto;
import org.una.UNAeropuerto.entities.Provedor;
import org.una.UNAeropuerto.repositories.IProvedorRepository;
import org.una.UNAeropuerto.utils.MapperUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProvedorServiceImplementation implements IProvedorService {

    @Autowired
    IProvedorRepository provedorRepository;

    @Override
    @Transactional(readOnly = true)
    public ProvedorDto getById(long id) {
        Optional<Provedor> result = provedorRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), ProvedorDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProvedorDto> getByNombre(String nombre) {
        Optional<List<Provedor>> result = provedorRepository.findByNombreContaining(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ProvedorDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProvedorDto> findByActivos(boolean activo) {
        Optional<List<Provedor>> result = provedorRepository.findByActivo(activo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ProvedorDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public ProvedorDto create(ProvedorDto provedor) {
        Provedor entityProvedor = MapperUtils.entityFromDto(provedor, Provedor.class);
        entityProvedor = provedorRepository.save(entityProvedor);
        return MapperUtils.DtoFromEntity(entityProvedor, ProvedorDto.class);
    }

    @Override
    @Transactional
    public ProvedorDto update(ProvedorDto provedor) {
        Optional<Provedor> result = provedorRepository.findById(provedor.getId());
        if (result.isPresent()) {
            Provedor entity = MapperUtils.entityFromDto(provedor, Provedor.class);
            entity = provedorRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, ProvedorDto.class);
        }
        return null;
    }
}
