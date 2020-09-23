package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.DetalleServicioDto;
import org.una.UNAeropuerto.dto.DetalleServicioDto;
import org.una.UNAeropuerto.entities.DetalleServicio;
import org.una.UNAeropuerto.entities.Tipo;

import java.util.List;
import java.util.Optional;


public interface IDetalleServicioService {

    public  DetalleServicioDto getById(long id);

    public List<DetalleServicioDto> findByActivos(boolean activo);
    
    public List<DetalleServicioDto> findByTiposId(long id);

    public DetalleServicioDto update(DetalleServicioDto detalleServicioDto);

    public DetalleServicioDto create(DetalleServicioDto detalleServicioDto);
}
