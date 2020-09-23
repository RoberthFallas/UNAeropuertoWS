/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Rol;

/**
 *
 * @author Roberth :)
 */
public interface IRolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombre);

    public Optional<List<Rol>> findByNombreContaining(String nombre);

    public Optional<List<Rol>> findByDescripcionContaining(String descripcion);

    public Optional<List<Rol>> findByActivoLike(boolean activo);

}
