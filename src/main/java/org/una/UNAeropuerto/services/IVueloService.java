/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.Date;
import java.util.List;
import org.una.UNAeropuerto.dto.VueloDto;

/**
 *
 * @author Roberth :)
 */
public interface IVueloService {

    public VueloDto getById(long id);

    public VueloDto getByNombreVuelo(String nombre);

    public List<VueloDto> findByEstado(byte estado);

    public List<VueloDto> findByNombre(String nombre);

    public List<VueloDto> findEntreFechaYHora(Date start, Date end);

    public List<VueloDto> findEntreFechas(Date start, Date end);

    public List<VueloDto> findByFechaVuelo(Date fecha);

    public Boolean isAvionLibre(Date start, Date end, long idVuelo, long idAvion);

    public VueloDto create(VueloDto vuelo);

    public VueloDto update(VueloDto vuelo);

    public List<VueloDto> filter(String aerolinea, String nombreVuelo, String matriculaAvion, String llegada, String salida, Date desde, Date hasta);

    public List<VueloDto> findByIdUsingListParam(List<Long> idList);
}
