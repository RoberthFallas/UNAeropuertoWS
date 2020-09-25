/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Direccion;

/**
 *
 * @author Roberth :)
 */
public interface IDireccionRepository extends JpaRepository<Direccion, Long> {

    public Optional<List<Direccion>> findByDireccionVuelo(String direccionVuelo);

    public Optional<List<Direccion>> findByLugaresIdId(long id);
}
