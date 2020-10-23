package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Notificacion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface INotificacionRepository extends JpaRepository<Notificacion, Long> {

    Optional<List<Notificacion>> findByAreasIdId(long id);

    Optional<List<Notificacion>> findByActivo(boolean estado);

    @Query("select u from Notificacion u where u.areasId=:id_area and u.activo=:activo")
    public Optional<List<Notificacion>> buscarIdandEstado(@Param("id_area") long area, @Param("activo") boolean activo);

    public Optional<List<Notificacion>> findByAreasIdIdAndActivo(Long permisoId, boolean estado);

}
