package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Notificacion;

import java.util.List;
import java.util.Optional;

public interface INotificacionRepository extends JpaRepository<Notificacion, Long> {

    Optional<List<Notificacion>> findByAreasIdId(long id);

    Optional<List<Notificacion>> findByActivo(boolean estado);

}
