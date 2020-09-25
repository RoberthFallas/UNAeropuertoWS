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

    @Query("select v from Vuelo v where (v.horaSalida between :start and :end) or (v.horaLlegada between :start and :end)")
    Optional<List<Vuelo>> findBitweenHoraYFecha(@Param("start") Date start, @Param("end") Date end);

    @Query("select v from Vuelo v where (DATE(v.horaSalida) between :start and :end) or (DATE(v.horaLlegada) between :start and :end)")
    Optional<List<Vuelo>> findByBetweenDates(@Param("start") Date start, @Param("end") Date end);

    @Query("select v from Vuelo v where (DATE(v.horaSalida) = :fecha) or (DATE(v.horaLlegada) = :fecha)")
    Optional<List<Vuelo>> findByFechaVuelo(@Param("fecha") Date fecha);

}
