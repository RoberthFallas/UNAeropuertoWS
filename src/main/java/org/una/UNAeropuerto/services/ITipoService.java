package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.TipoReparacionDto;

import java.util.List;

public interface ITipoService {

    public TipoReparacionDto getById(long id);

    public TipoReparacionDto getByNombre(String nombre);

    public List<TipoReparacionDto> findByNombre(String nombre);

    public List<TipoReparacionDto> findByActivos(boolean activo);

    public TipoReparacionDto update(TipoReparacionDto tipo);

    public TipoReparacionDto create(TipoReparacionDto tipo);
}
