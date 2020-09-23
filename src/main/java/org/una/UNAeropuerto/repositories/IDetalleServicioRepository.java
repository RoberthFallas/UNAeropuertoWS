package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.DetalleServicio;
import org.una.UNAeropuerto.entities.Tipo;


import java.util.List;
import java.util.Optional;

public interface IDetalleServicioRepository extends JpaRepository<DetalleServicio, Long> {

    public Optional<List<DetalleServicio>> findByActivoLike(boolean activo);

    public Optional<List<DetalleServicio>> findByTiposId(long id);
}
