package org.una.UNAeropuerto.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.GastoReparacionDto;
import org.una.UNAeropuerto.dto.DetalleServicioDto;
import org.una.UNAeropuerto.dto.GastoReparacionDto;
import org.una.UNAeropuerto.dto.RolDto;
import org.una.UNAeropuerto.entities.GastoReparacion;
import org.una.UNAeropuerto.entities.DetalleServicio;
import org.una.UNAeropuerto.entities.GastoReparacion;
import org.una.UNAeropuerto.entities.Rol;
import org.una.UNAeropuerto.repositories.IDetalleServicioRepository;
import org.una.UNAeropuerto.repositories.IGastoReparacionRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class GastoReparacionServiceImplementation implements  IGastoReparacionService {

    @Autowired
    IGastoReparacionRepository gastoReparacionRepository;


    @Override
    @Transactional(readOnly = true)
    public GastoReparacionDto getById(long id) {
        Optional<GastoReparacion> result = gastoReparacionRepository.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), GastoReparacionDto.class);
        }
        return null;
    }



    @Override
    @Transactional(readOnly = true)
    public GastoReparacionDto getByNumeroContrato(Long numeroContrato) {
        Optional<GastoReparacion> result = gastoReparacionRepository.findByNumeroContrato(numeroContrato);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), GastoReparacionDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastoReparacionDto> findByEstado(boolean estado) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByActivoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastoReparacionDto> findByEstadoPago(boolean estado) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByEstadoPagoLike(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastoReparacionDto> findByAreaId(long id) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByAreasId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastoReparacionDto> findByFechaRegistroBetween(Date startDate, Date endDate) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByFechaRegistroBetween(startDate, endDate);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public GastoReparacionDto update(GastoReparacionDto gastoReparacionDto) {
        Optional<GastoReparacion> result = gastoReparacionRepository.findById(gastoReparacionDto.getId());
        if (result.isPresent()) {
            GastoReparacion entity = MapperUtils.entityFromDto(gastoReparacionDto, GastoReparacion.class);
            entity = gastoReparacionRepository.save(entity);
            return MapperUtils.DtoFromEntity(entity, GastoReparacionDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public GastoReparacionDto create(GastoReparacionDto usuario) {
        GastoReparacion entityUser = MapperUtils.entityFromDto(usuario, GastoReparacion.class);
        entityUser = gastoReparacionRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, GastoReparacionDto.class);
    }
}
