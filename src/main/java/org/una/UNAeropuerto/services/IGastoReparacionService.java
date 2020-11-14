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

    public List<GastoReparacionDto> findBetweenDiasPeriocidad(Integer start, Integer end);

    public List<GastoReparacionDto> findBweteenDiasDuracion(Integer start, Integer end);

    public List<GastoReparacionDto> busquedaMixta(String numContrato, String tipo, String proveedor, String activo, String pago, String startDate, String endDate, String startDuracion, String endDuracion, String startPeriocidad, String endPeriocidad) ;

    public List<GastoReparacionDto> findByTipoNombre(String nombre);

    public GastoReparacionDto update(GastoReparacionDto gastoReparacionDto);

    public GastoReparacionDto create(GastoReparacionDto gastoReparacionDto);

    public List<GastoReparacionDto> findByIdUsingListParam(List<Long> idList);

}
