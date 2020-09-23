/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Alerta;

/**
 *
 * @author Roberth :)
 */
public interface IAlertaRepository extends JpaRepository<Alerta, Long> {

    public Optional<Alerta> findById(long id);

    public Optional<List<Alerta>> findByTipo(byte id);

    public Optional<List<Alerta>> findByTituloContaining(String id);

    public Optional<List<Alerta>> findByEmisorContaining(String id);

    public Optional<List<Alerta>> findByAreaIdId(long id);

}
