package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.NotificacionDto;
import org.una.UNAeropuerto.entities.Notificacion;
import org.una.UNAeropuerto.repositories.INotificacionRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImplementation implements INotificacionService {

    @Autowired
    INotificacionRepository notificacionRepository;

    @Override
    @Transactional(readOnly = true)
    public NotificacionDto getById(long id) {
        Optional<Notificacion> result = notificacionRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), NotificacionDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDto> findByActivos(boolean estado) {
        Optional<List<Notificacion>> result = notificacionRepository.findByActivo(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), NotificacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    public List<NotificacionDto> findByAreaId(long id) {
        Optional<List<Notificacion>> result = notificacionRepository.findByAreasIdId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), NotificacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public NotificacionDto create(NotificacionDto notificacion) {
        Notificacion entityUser = MapperUtils.entityFromDto(notificacion, Notificacion.class);
        entityUser = notificacionRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, NotificacionDto.class);
    }

    @Override
    @Transactional
    public NotificacionDto upDate(NotificacionDto update) {
        Optional<Notificacion> result = notificacionRepository.findById(update.getId());
        if (result.isPresent()) {
            Notificacion entity = MapperUtils.entityFromDto(update, Notificacion.class);
            entity = notificacionRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, NotificacionDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionDto> buscarAreaAndestado(long id,boolean estado) {
       Optional<List<Notificacion>> result = notificacionRepository.findByAreasIdIdAndActivo(id, estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), NotificacionDto.class);
        }
        return new ArrayList();
    }
}
