package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.GastoReparacionDto;

import java.util.Date;
import java.util.List;

public interface IGastoReparacionService {

    public GastoReparacionDto getById(long id);

    public GastoReparacionDto getByNumeroContrato(Long numeroContrato);

    public List<GastoReparacionDto> findByEstado(boolean estado);

    public List<GastoReparacionDto> findByEstadoPago(boolean estado);

    public List<GastoReparacionDto> findByAreaId(long id);

    public List<GastoReparacionDto> findByFechaRegistroBetween(Date startDate, Date endDate);

    public GastoReparacionDto update(GastoReparacionDto gastoReparacionDto);

    public GastoReparacionDto create(GastoReparacionDto gastoReparacionDto);

}
