package org.una.UNAeropuerto.services;
import org.una.UNAeropuerto.dto.TipoServicioDto;



import java.util.List;
import java.util.Optional;

public interface ITipoServicioService {

    public TipoServicioDto getByNombre(String nombre);

    public TipoServicioDto getById(long id);

    public List<TipoServicioDto> findByNombreAproximado(String nombre);

    public List<TipoServicioDto> findByDescripcionAproximado(String descripcion);

    public List<TipoServicioDto> findByEstado(boolean estado);

    public TipoServicioDto update(TipoServicioDto cobro);

    public TipoServicioDto create(TipoServicioDto cobro);
}
