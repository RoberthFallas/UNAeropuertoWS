package org.una.UNAeropuerto.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Provedor;
import org.una.UNAeropuerto.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface IProvedorRepository extends JpaRepository<Provedor, Long> {
    public Optional<Provedor> findByNombre(String nombre);

    public Optional<List<Provedor>> findByActivoLike(boolean activo);
}
