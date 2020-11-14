package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.ProvedorDto;
import java.util.List;

public interface IProvedorService {

    public ProvedorDto getById(long id);

    public List<ProvedorDto> getByNombre(String nombre);

    public List<ProvedorDto> findByActivos(boolean activo);

    public ProvedorDto update(ProvedorDto usuario);

    public ProvedorDto create(ProvedorDto usuario);
}
