package org.una.UNAeropuerto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Tipo;

import java.util.List;
import java.util.Optional;

public interface ITipoRepository extends JpaRepository<Tipo, Long> {
    public Optional<Tipo> findByNombre(String nombre);

    public Optional<List<Tipo>> findByNombreContaining(String nombre);

    public Optional<List<Tipo>> findByActivoLike(boolean activo);



}
