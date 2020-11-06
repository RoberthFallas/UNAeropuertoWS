package org.una.UNAeropuerto.services;

import io.swagger.models.auth.In;
import org.springframework.data.repository.query.Param;
import org.una.UNAeropuerto.dto.GastoReparacionDto;

import java.util.Date;
import java.util.List;

public interface IGastoReparacionService {

    public GastoReparacionDto getById(long id);

    public  GastoReparacionDto getByNumeroContrato(Long numeroContrato);

    public List<GastoReparacionDto> findByEstado(boolean estado);

    public List<GastoReparacionDto> findByEstadoPago(boolean estado);

    public List<GastoReparacionDto> findByAreaId(long id);

    public List<GastoReparacionDto> findByFechaRegistroBetween(Date startDate, Date endDate);

    public List<GastoReparacionDto> findBetweenDiasPeriocidad(Integer start, Integer end);

    public List<GastoReparacionDto> findBweteenDiasDuracion(Integer start, Integer end);

    public List<GastoReparacionDto> busquedaMixta(String numContrato, String tipo, String proveedor, String activo, String activo2, String pago, String pago2, String date1, String date2, String duracion1, String duracion2, String periocidad1, String periocidad2);

    public List<GastoReparacionDto> findByTipoNombre(String nombre);

    public GastoReparacionDto update(GastoReparacionDto gastoReparacionDto);

    public GastoReparacionDto create(GastoReparacionDto gastoReparacionDto);

    public List<GastoReparacionDto> findByIdUsingListParam(List<Long> idList);

}
