/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Area;

/**
 *
 * @author Roberth :)
 */
public interface IAreaRepository extends JpaRepository<Area, Long> {

    public Optional<Area> findByNombre(String nombre);

    public Optional<Area> findByDescripcion(String descripcion);

    public Optional<List<Area>> findByNombreContaining(String nombre);

    public Optional<List<Area>> findByDescripcionContaining(String descripcion);

}
