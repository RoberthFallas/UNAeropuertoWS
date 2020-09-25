/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Pista;

/**
 *
 * @author Roberth :)
 */
public interface IPistaRepository extends JpaRepository<Pista, Long> {

    public Optional<Pista> findByNumeroPista(String numeroPista);

    public Optional<List<Pista>> findByNumeroPistaContaining(String numeroPista);

    public Optional<List<Pista>> findByActivo(boolean state);

    public Optional<List<Pista>> findByLongitud(float longitud);

}
