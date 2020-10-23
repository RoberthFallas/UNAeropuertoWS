package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.TipoServicio;

import java.util.List;
import java.util.Optional;

public interface ITipoServicioRepository extends JpaRepository<TipoServicio, Long> {

    public Optional<TipoServicio> findByNombre(String nombre);

    public Optional<List<TipoServicio>> findByNombreContaining(String nombre);

    public Optional<List<TipoServicio>> findByDescripcionContaining(String descripcion);

    public Optional<List<TipoServicio>> findByActivoLike(boolean estado);

    //public List<TipoServicio> findAll();


}
