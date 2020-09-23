package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.UNAeropuerto.dto.AreaDto;
import org.una.UNAeropuerto.dto.ProvedorDto;
import org.una.UNAeropuerto.entities.Area;
import org.una.UNAeropuerto.entities.Provedor;
import org.una.UNAeropuerto.repositories.IAreaRepository;
import org.una.UNAeropuerto.repositories.IProvedorRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProvedorServiceImplementation implements  IProvedorService {

    @Autowired
    IProvedorRepository provedorRepository;

    @Override
    public ProvedorDto getById(long id) {
        Optional<Provedor> result = provedorRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), ProvedorDto.class);
        }
        return null;
    }

    @Override
    public ProvedorDto getByNombre(String nombre) {
        Optional<Provedor> result = provedorRepository.findByNombre(nombre);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), ProvedorDto.class);
        }
        return null;
    }

    @Override
    public List<ProvedorDto> findByActivos(boolean activo) {
        Optional<List<Provedor>> result = provedorRepository.findByActivoLike(activo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ProvedorDto.class);
        }
        return new ArrayList();
    }

    @Override
    public ProvedorDto update(ProvedorDto usuario) {
        return null;
    }

    @Override
    public ProvedorDto create(ProvedorDto usuario) {
        return null;
    }
}
