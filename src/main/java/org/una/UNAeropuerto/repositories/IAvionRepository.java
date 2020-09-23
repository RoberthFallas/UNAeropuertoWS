package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Avion;

import java.util.List;
import java.util.Optional;

public interface IAvionRepository extends JpaRepository<Avion, Long> {

    public Optional<List<Avion>> findByActivoLike(boolean activo);
    public Optional<Avion> findByMatricula(String matricula);
    public Optional<List<Avion>> findByAerolineasId(long id);




}
