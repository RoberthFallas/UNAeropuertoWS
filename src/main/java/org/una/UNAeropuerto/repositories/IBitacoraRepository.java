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
import org.una.UNAeropuerto.entities.Bitacora;

/**
 *
 * @author Roberth :)
 */
public interface IBitacoraRepository extends JpaRepository<Bitacora, Long> {

    public Optional<List<Bitacora>> findByTipoCambioContaining(String tipoCambio);

    public Optional<List<Bitacora>> findByusuariosIdId(long id);

    public Optional<List<Bitacora>> findByActivaLike(boolean activa);

    @Query("select b from Bitacora b where DATE(b.fechaModificacion) like :fechaModificacion")
    public Optional<List<Bitacora>> findByFechaModificacion(@Param("fechaModificacion") Date fechaModificacion);

    @Query("select b from Bitacora b where b.fechaModificacion between :fechaInicio and :fechaFinal")
    public Optional<List<Bitacora>> findBetweenDates(@Param("fechaInicio") Date fechaInicio, @Param("fechaFinal") Date fechaFinal);
}
