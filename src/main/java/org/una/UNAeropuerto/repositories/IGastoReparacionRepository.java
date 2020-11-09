package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.dto.GastoReparacionDto;
import org.una.UNAeropuerto.entities.GastoReparacion;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IGastoReparacionRepository extends JpaRepository<GastoReparacion, Long> {

    public Optional<List<GastoReparacion>> findByActivo(boolean activo);

    public Optional<List<GastoReparacion>> findByAreasIdId(Long id);

    public Optional<List<GastoReparacion>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Optional<List<GastoReparacion>> findByEstadoPago(boolean estado);

    public Optional<GastoReparacion> findByNumeroContrato(long numero);

    public Optional<List<GastoReparacion>> findByTiposIdNombreContaining(String nombre);

    public Optional<List<GastoReparacion>> findByDuracionBetween(Integer inicio, Integer fin);

    public Optional<List<GastoReparacion>> findByPeriodicidadBetween(Integer inicio, Integer fin);

    @Query("select g from GastoReparacion g where g.id in :list")
    public List<GastoReparacion> findByIdUsingListParam(@Param("list") List<Long> idList);
    
    @Query("select gr from GastoReparacion gr join gr.provedoresId  p  "
            + " join gr.tiposId  tr where "
            + " UPPER(gr.numeroContrato)like CONCAT('%',UPPER(:numContrato),'%')"
            + " and UPPER(tr.nombre) like CONCAT('%',UPPER(:tipo),'%')"
            + " and UPPER(p.nombre) like CONCAT('%',UPPER(:proveedor),'%')"
            + " and (gr.activo = :activo or gr.activo = :activo2)"
            + " and (gr.estadoPago = :pago or gr.estadoPago = :pago2)"
            + " and gr.fechaRegistro between :date1 and :date2"
            + " and gr.duracion between  :duracion1 and :duracion2"
            + " and gr.periodicidad between  :periocidad1 and :periocidad2")
    public Optional<List<GastoReparacion>> busquedaMixtaTodosLosEstados(@Param("numContrato") String numContrato, @Param("tipo") String tipo, @Param("proveedor") String  proveedor, @Param("activo") boolean activo, @Param("activo2") boolean activo2, @Param("pago") boolean pago, @Param("pago2") boolean pago2, @Param("date1")  Date date1, @Param("date2")  Date date2, @Param("duracion1")  int duracion1, @Param("duracion2")  int duracion2  , @Param("periocidad1")  int periocidad1, @Param("periocidad2")  int periocidad2    );
}
