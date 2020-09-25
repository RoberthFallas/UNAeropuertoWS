package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.TipoDto;

import java.util.List;


public interface ITipoService{

    public TipoDto getById(long id);

    public TipoDto getByNombre(String nombre);

    public List<TipoDto> findByNombre(String nombre);

    public List<TipoDto> findByActivos(boolean activo);

    public TipoDto update(TipoDto tipo);

    public TipoDto create(TipoDto tipo);
}
