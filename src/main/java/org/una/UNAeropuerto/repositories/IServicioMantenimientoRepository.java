package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.ServicioMantenimiento;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IServicioMantenimientoRepository extends JpaRepository<ServicioMantenimiento, Long> {

    public Optional<List<ServicioMantenimiento>> findByFechaServicioBetween(Date startDate, Date endDate);

    public Optional<List<ServicioMantenimiento>> findByNumeroFacturaContaining(long numeroFactura);

    public Optional<List<ServicioMantenimiento>> findByEstadoPago(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByActivo(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByEstaFinalizacion(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByAvionesIdMatriculaContaining(String matricula);

    @Query("select sm from ServicioMantenimiento sm where sm.id in :list")
    public List<ServicioMantenimiento> findByIdUsingListParam(@Param("list") List<Long> list);

    public Optional<List<ServicioMantenimiento>> findByTiposServiciosIdNombreContaining(String nombre);

}
