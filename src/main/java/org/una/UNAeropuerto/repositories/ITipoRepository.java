package org.una.UNAeropuerto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.TipoReparacion;

import java.util.List;
import java.util.Optional;

public interface ITipoRepository extends JpaRepository<TipoReparacion, Long> {
    public Optional<TipoReparacion> findByNombre(String nombre);

    public Optional<List<TipoReparacion>> findByNombreContaining(String nombre);

    public Optional<List<TipoReparacion>> findByActivo(boolean activo);



}
