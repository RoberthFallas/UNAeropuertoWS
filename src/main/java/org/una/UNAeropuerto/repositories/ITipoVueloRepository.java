/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.List;
import org.una.UNAeropuerto.entities.TipoVuelo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author roberth :)
 */
public interface ITipoVueloRepository extends JpaRepository<TipoVuelo, Long> {

    public List<TipoVuelo> findByActivo(boolean activo);
}
