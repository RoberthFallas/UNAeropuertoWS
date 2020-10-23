package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.NotificacionDto;
import org.una.UNAeropuerto.dto.LugarDto;

import java.util.List;

public interface INotificacionService {

    public NotificacionDto getById(long id);

    public List<NotificacionDto> findByActivos(boolean estado);

    public List<NotificacionDto> findByAreaId(long id);

    public List<NotificacionDto> buscarAreaAndestado(long id,boolean estado);

    public NotificacionDto create(NotificacionDto cobro);

    public NotificacionDto upDate(NotificacionDto update);
}
