package org.una.UNAeropuerto.repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Avion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAvionRepository extends JpaRepository<Avion, Long> {

    public Optional<List<Avion>> findByActivoLike(boolean activo);

    public Optional<Avion> findByMatricula(String matricula);

    public Optional<List<Avion>> findByMatriculaContaining(String valor);

    public Optional<List<Avion>> findByAerolineasIdNombreContaining(String nombre);

    @Query("select av from Avion av join av.aerolineasId aero"
            + " where UPPER(av.matricula) like CONCAT('%',UPPER(:matr),'%')"
            + " and UPPER(aero.nombre) like CONCAT('%',UPPER(:aerol),'%')"
            + " and aero.actiov = true")
    public List<Avion> filterByMatriculaAndAerolinea(@Param("matr") String matricula, @Param("aerol") String aerolinea);

    @Query("select av from Avion av join av.vueloList vu where "
            + "((vu.horaSalida between :str and :end) or "
            + "(vu.horaLlegada between :str and :end)) and av.id = :id")
    public List<Avion> findAvionConVueloEntreFechas(@Param("str") Date start, @Param("end") Date end, @Param("id") Long id);
}
