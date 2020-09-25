package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.DetalleServicioDto;
import java.util.List;

public interface IDetalleServicioService {

    public DetalleServicioDto getById(long id);

    public List<DetalleServicioDto> findByActivos(boolean activo);

    public List<DetalleServicioDto> findByTiposId(long id);

    public DetalleServicioDto update(DetalleServicioDto detalleServicioDto);

    public DetalleServicioDto create(DetalleServicioDto detalleServicioDto);
}
