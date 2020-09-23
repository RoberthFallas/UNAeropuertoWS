package org.una.UNAeropuerto.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.GastoReparacion;
import org.una.UNAeropuerto.entities.Rol;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IGastoReparacionRepository extends JpaRepository<GastoReparacion, Long> {


    public Optional<List<GastoReparacion>> findByActivoLike(boolean activo);

    public Optional<List<GastoReparacion>> findByAreasId(Long id);

    public Optional<List<GastoReparacion>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Optional<List<GastoReparacion>> findByEstadoPagoLike(boolean estado);

    public Optional<GastoReparacion> findByNumeroContrato(long nombre);




}
