package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.ServicioMantenimiento;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IServicioMantenimientoRepository extends JpaRepository<ServicioMantenimiento, Long> {


    public Optional<List<ServicioMantenimiento>> findByFechaServicioBetween(Date startDate, Date endDate);

    public Optional<ServicioMantenimiento>findByNumeroFactura(Long numeroFactura);

    public Optional<List<ServicioMantenimiento>> findByEstadoPago(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByActivo(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByEstaFinalizacion(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByAvionesIdMatriculaContaining(String matricula);


    public Optional<List<ServicioMantenimiento>> findByTiposServiciosIdNombreContaining(String nombre);



}
