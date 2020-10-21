/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Aerolinea;

/**
 *
 * @author Roberth :)
 */
public interface IAerolineaRepository extends JpaRepository<Aerolinea, Long> {

    public Optional<Aerolinea> findByNombre(String nombre);

    public List<Aerolinea> findByNombreContaining(String nombre);

    public List<Aerolinea> findByActiov(boolean estado);
}
