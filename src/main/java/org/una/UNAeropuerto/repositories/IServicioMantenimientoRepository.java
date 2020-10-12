package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.ServicioMantenimiento;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IServicioMantenimientoRepository extends JpaRepository<ServicioMantenimiento, Long> {


    public Optional<List<ServicioMantenimiento>> findByFechaServicioBetween(Date startDate, Date endDate);

    public Optional<ServicioMantenimiento> findByNumeroFactura(long numeroFactura);

    public Optional<List<ServicioMantenimiento>> findByEstadoPagoLike(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByActivoLike(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByEstaFinalizacionLike(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByAvionesId(long id);


    public Optional<List<ServicioMantenimiento>> findByTiposServiciosId(long id);



}
