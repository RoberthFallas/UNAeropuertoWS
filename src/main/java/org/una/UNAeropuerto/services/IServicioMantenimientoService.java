package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import java.util.Date;
import java.util.List;

public interface IServicioMantenimientoService {

    public ServicioMantenimientoDto getByNumeroFactura(Long numeroFactura);

    public ServicioMantenimientoDto getById(long id);

    public List<ServicioMantenimientoDto> findByFechaServicioBetween(Date startDate, Date endDate);

    public List<ServicioMantenimientoDto> findByEstadoPago(boolean estado);

    public List<ServicioMantenimientoDto> findByEstado(boolean estado);

    public List<ServicioMantenimientoDto> findByEstadoFinalizacion(boolean estado);

    public List<ServicioMantenimientoDto> findByAvionesMatricula(String matricula);


    public List<ServicioMantenimientoDto> findByTiposServiciosNombre(String nombre);

    public ServicioMantenimientoDto update(ServicioMantenimientoDto servicioMantenimientoDto);

    public ServicioMantenimientoDto create(ServicioMantenimientoDto servicioMantenimientoDto);
}
