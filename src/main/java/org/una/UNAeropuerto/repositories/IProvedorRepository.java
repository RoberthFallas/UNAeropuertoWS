package org.una.UNAeropuerto.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Provedor;

import java.util.List;
import java.util.Optional;

public interface IProvedorRepository extends JpaRepository<Provedor, Long> {
    public Optional<List<Provedor>> findByNombreContaining(String nombre);

    public Optional<List<Provedor>> findByActivo(boolean activo);
}
