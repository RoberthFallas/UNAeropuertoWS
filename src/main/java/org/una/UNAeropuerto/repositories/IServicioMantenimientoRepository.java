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

    public Optional<ServicioMantenimiento> findByNumeroFactura(Long numeroFactura);

    public Optional<List<ServicioMantenimiento>> findByEstadoPago(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByActivo(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByEstaFinalizacion(boolean estado);

    public Optional<List<ServicioMantenimiento>> findByAvionesIdMatriculaContaining(String matricula);

    @Query("select sm from ServicioMantenimiento sm where sm.id in :list")
    public List<ServicioMantenimiento> findByIdUsingListParam(@Param("list") List<Long> list);

    public Optional<List<ServicioMantenimiento>> findByTiposServiciosIdNombreContaining(String nombre);

    @Query("select sm from ServicioMantenimiento sm join sm.avionesId  av  "
            + " join sm.tiposServiciosId  ts where "
            + "  UPPER(av.matricula)like CONCAT('%',UPPER(:matr),'%')"
            + " and UPPER(ts.nombre) like CONCAT('%',UPPER(:tipo),'%')"
            + " and UPPER(sm.numeroFactura) like CONCAT('%',UPPER(:numFactura),'%')"
            + " and (sm.activo = :activo or sm.activo = :activo2)"
            + " and (sm.estadoPago = :pago or sm.estadoPago = :pago2)"
            + " and (sm.estaFinalizacion = :finalizacion or sm.estaFinalizacion = :finalizacion2)"
            + " and sm.fechaServicio between :date1 and :date2")
    public Optional<List<ServicioMantenimiento>> busquedaMixtaTodosLosEstados(@Param("matr") String matr, @Param("tipo") String tipo, @Param("numFactura") String numFactura, @Param("activo") boolean activo, @Param("activo2") boolean activo2, @Param("pago") boolean pago, @Param("pago2") boolean pago2, @Param("finalizacion") boolean finalizacion, @Param("finalizacion2") boolean finalizacion2, @Param("date1") Date date1, @Param("date2") Date date2);

}
