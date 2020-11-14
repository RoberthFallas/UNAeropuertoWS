/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.UNAeropuerto.entities.Vuelo;

/**
 *
 * @author Roberth :)
 */
public interface IVueloRepository extends JpaRepository<Vuelo, Long> {

    Optional<Vuelo> findByNombreVuelo(String nombre);

    Optional<List<Vuelo>> findByEstado(byte estado);

    Optional<List<Vuelo>> findByNombreVueloContaining(String nombre);

    @Query("select v from Vuelo v where v.id in :list")
    List<Vuelo> findByIdUsingListParam(@Param("list") List<Long> list);

    @Query("select v from Vuelo v where (v.horaSalida between :start and :end) or (v.horaLlegada between :start and :end)")
    List<Vuelo> findBitweenHoraYFecha(@Param("start") Date start, @Param("end") Date end);

    @Query("select v from Vuelo v where (DATE(v.horaSalida) between :start and :end) or (DATE(v.horaLlegada) between :start and :end)")
    List<Vuelo> findByBetweenDates(@Param("start") Date start, @Param("end") Date end);

    @Query("select v from Vuelo v where (DATE(v.horaSalida) = :fecha) or (DATE(v.horaLlegada) = :fecha)")
    Optional<List<Vuelo>> findByFechaVuelo(@Param("fecha") Date fecha);

    @Query("select v from Vuelo v join v. avionesId av where "
            + "((:start between v.horaSalida and v.horaLlegada) or "
            + "(:end between v.horaSalida and v.horaLlegada) or "
            + "(v.horaSalida between :start and :end) or "
            + "(v.horaLlegada between :start and :end)) and av.id = :avId and v.estado <> 3")
    List<Vuelo> findBitweenHoraYFechaByAvion(@Param("start") Date start, @Param("end") Date end, @Param("avId") long avionId);

    @Query("select v from Vuelo v join v.avionesId av join av.aerolineasId ae "
            + "join v.lugarSalida ls join v.lugarLlegada ll "
            + "where UPPER(ae.nombre) like CONCAT('%', UPPER(:aero),'%') and "
            + "UPPER(v.nombreVuelo) like CONCAT('%', UPPER(:nv),'%') and "
            + "UPPER(av.matricula) like CONCAT('%', UPPER(:mtr),'%') and "
            + "UPPER(ll.nombre) like CONCAT('%', UPPER(:lleg),'%') and "
            + "UPPER(ls.nombre) like CONCAT('%', UPPER(:sal),'%') and "
            + "v.estado <> 3")
    List<Vuelo> findByTextParameters(@Param("aero") String aerolinea, @Param("nv") String nombreVuelo, @Param("mtr") String matriculaAvion, @Param("lleg") String llegada, @Param("sal") String salida);

    @Query("select COUNT(v) from Vuelo v join v.lugarSalida ls join v.lugarLlegada ll "
            + "where ((ls.id = :lug_ap and ABS(TIMESTAMPDIFF(MINUTE, v.horaSalida, :exeDate)) <= :safe_t) or "
            + "(ll.id = :lug_ap and ABS(TIMESTAMPDIFF(MINUTE, v.horaLlegada, :exeDate)) <= :safe_t)) and "
            + "v.id <> :fly_id and v.estado <> 3")
    Long countVuelosCercanos(@Param("exeDate") Date executionDate, @Param("lug_ap") Long lugarAerPId, @Param("safe_t") Integer safeTime, @Param("fly_id") long flyId);

}