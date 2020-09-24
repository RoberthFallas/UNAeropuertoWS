/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Lugar;

/**
 *
 * @author Roberth :)
 */
public interface ILugarRepository extends JpaRepository<Lugar, Long> {

    public Optional<Lugar> findByNombre(String nombre);

    public Optional<List<Lugar>> findByNombreContaining(String nombre);

    public Optional<List<Lugar>> findByActivo(boolean activo);

}
