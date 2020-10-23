package org.una.UNAeropuerto.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.GastoReparacion;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IGastoReparacionRepository extends JpaRepository<GastoReparacion, Long> {


    public Optional<List<GastoReparacion>> findByActivo(boolean activo);

    public Optional<List<GastoReparacion>> findByAreasIdId(Long id);

    public Optional<List<GastoReparacion>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Optional<List<GastoReparacion>> findByEstadoPago(boolean estado);

    public  Optional<GastoReparacion> findByNumeroContrato(long numero);

    public Optional<List<GastoReparacion>> findByTiposIdNombreContaining(String nombre);

    public Optional<List<GastoReparacion>> findByDuracionBetween(Integer inicio, Integer fin);

    public Optional<List<GastoReparacion>> findByPeriodicidadBetween(Integer inicio, Integer fin);









}
