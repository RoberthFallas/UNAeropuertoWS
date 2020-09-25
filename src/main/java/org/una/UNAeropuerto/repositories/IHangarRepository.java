package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Area;
import org.una.UNAeropuerto.entities.Cobro;
import org.una.UNAeropuerto.entities.Hangar;

import java.util.List;
import java.util.Optional;

public interface IHangarRepository extends JpaRepository<Hangar, Long> {
    public Optional<List<Hangar>> findByActivoLike(boolean estado);

    public Optional<List<Hangar>> findByEspecialidadContaining(String parametro);
}
