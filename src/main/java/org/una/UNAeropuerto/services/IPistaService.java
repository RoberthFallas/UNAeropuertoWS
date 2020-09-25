/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.PistaDto;

/**
 *
 * @author Roberth :)
 */
public interface IPistaService {

    public PistaDto getById(long id);

    public PistaDto getByNumeroPista(String numeroPista);

    public List<PistaDto> findByNumeroPista(String numeroPista);

    public List<PistaDto> findByEstado(boolean estate);

    public PistaDto create(PistaDto pista);

    public PistaDto update(PistaDto pista);
}
