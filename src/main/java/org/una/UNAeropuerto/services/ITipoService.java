package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.TipoDto;
import org.una.UNAeropuerto.dto.TipoDto;
import org.una.UNAeropuerto.entities.Tipo;

import java.util.List;
import java.util.Optional;

public interface ITipoService{

    public TipoDto getById(long id);

    public TipoDto getByNombre(String nombre);

    public List<TipoDto> findByNombre(String nombre);

    public List<TipoDto> findByActivos(boolean activo);

    public TipoDto update(TipoDto usuario);

    public TipoDto create(TipoDto usuario);
}
