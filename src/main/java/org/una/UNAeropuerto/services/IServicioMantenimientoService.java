package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.entities.ServicioMantenimiento;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IServicioMantenimientoService {


    public ServicioMantenimientoDto getByNumeroFactura(long numeroFactura);

    public ServicioMantenimientoDto getById(long id);

    public List<ServicioMantenimientoDto> findByFechaServicioBetween(Date startDate, Date endDate);

    public List<ServicioMantenimientoDto> findByEstadoPago(boolean estado);

    public List<ServicioMantenimientoDto> findByEstado(boolean estado);

    public List<ServicioMantenimientoDto> findByEstadoFinalizacion(boolean estado);

    public List<ServicioMantenimientoDto> findByAvionesId(long id);

    public List<ServicioMantenimientoDto> findByHangaresId(long id);

    public List<ServicioMantenimientoDto> findByTiposServiciosId(long id);

    public ServicioMantenimientoDto update(ServicioMantenimientoDto servicioMantenimientoDto);

    public ServicioMantenimientoDto create(ServicioMantenimientoDto servicioMantenimientoDto);
}
