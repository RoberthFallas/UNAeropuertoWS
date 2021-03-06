package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.TipoServicioDto;
import java.util.List;

public interface ITipoServicioService {

    public List<TipoServicioDto> getAll();
    public TipoServicioDto getByNombre(String nombre);

    public TipoServicioDto getById(long id);

    public List<TipoServicioDto> findByNombreAproximado(String nombre);

    public List<TipoServicioDto> findByDescripcionAproximado(String descripcion);

    public List<TipoServicioDto> findByEstado(boolean estado);

    public TipoServicioDto update(TipoServicioDto cobro);

    public TipoServicioDto create(TipoServicioDto cobro);
}
