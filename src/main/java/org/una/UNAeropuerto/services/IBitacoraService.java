/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.Date;
import java.util.List;
import org.una.UNAeropuerto.dto.BitacoraDto;

/**
 *
 * @author Roberth :)
 */
public interface IBitacoraService {

    public BitacoraDto getById(long id);

    public List<BitacoraDto> findByUserId(long id);

    public List<BitacoraDto> findByFechaBitacora(Date fecha);

    public List<BitacoraDto> findByEstado(boolean state);

    public List<BitacoraDto> findBetweenDates(Date startDate, Date endDate);

    public BitacoraDto changeStateById(long id, boolean state);

    public BitacoraDto create(BitacoraDto bitacora);
}