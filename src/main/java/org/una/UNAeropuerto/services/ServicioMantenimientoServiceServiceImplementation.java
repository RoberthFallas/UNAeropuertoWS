package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.entities.ServicioMantenimiento;
import org.una.UNAeropuerto.repositories.IServicioMantenimientoRepository;
import org.una.UNAeropuerto.utils.MapperUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioMantenimientoServiceServiceImplementation implements IServicioMantenimientoService {

    @Autowired
    IServicioMantenimientoRepository servicioMantenimientoRepository;

    @Override
    @Transactional(readOnly = true)
    public ServicioMantenimientoDto getByNumeroFactura(long numeroFactura) {
        Optional<ServicioMantenimiento> result = servicioMantenimientoRepository.findByNumeroFactura(numeroFactura);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), ServicioMantenimientoDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ServicioMantenimientoDto getById(long id) {
        Optional<ServicioMantenimiento> result = servicioMantenimientoRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), ServicioMantenimientoDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByFechaServicioBetween(Date startDate, Date endDate) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByFechaServicioBetween(startDate, endDate);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByEstadoPago(boolean estado) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByEstadoPagoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByEstado(boolean estado) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByActivoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByEstadoFinalizacion(boolean estado) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByEstaFinalizacionLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByAvionesId(long id) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByAvionesId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByHangaresId(long id) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByHangaresId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByTiposServiciosId(long id) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByTiposServiciosId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public ServicioMantenimientoDto update(ServicioMantenimientoDto servicioMantenimientoDto) {
        Optional<ServicioMantenimiento> result = servicioMantenimientoRepository.findById(servicioMantenimientoDto.getId());
        if (result.isPresent()) {
            ServicioMantenimiento entity = MapperUtils.entityFromDto(servicioMantenimientoDto, ServicioMantenimiento.class);
            entity = servicioMantenimientoRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, ServicioMantenimientoDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public ServicioMantenimientoDto create(ServicioMantenimientoDto servicioMantenimientoDto) {
        ServicioMantenimiento entityUser = MapperUtils.entityFromDto(servicioMantenimientoDto, ServicioMantenimiento.class);
        entityUser = servicioMantenimientoRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, ServicioMantenimientoDto.class);
    }
}
