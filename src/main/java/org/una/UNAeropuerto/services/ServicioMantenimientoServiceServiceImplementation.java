package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.entities.ServicioMantenimiento;
import org.una.UNAeropuerto.repositories.IServicioMantenimientoRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public  ServicioMantenimientoDto getByNumeroFactura(Long numeroFactura) {
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
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByEstadoPago(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByEstado(boolean estado) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByActivo(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByEstadoFinalizacion(boolean estado) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByEstaFinalizacion(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByAvionesMatricula(String matricula) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByAvionesIdMatriculaContaining(matricula);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> busquedaMixtaTodosEstados(String matricula, String tipo, String numFactura, String activo, String pago,String finalizacion, String dateDesde, String dateHasta) {
        matricula = !"none".equals(matricula) ? matricula : "";
        tipo = !"none".equals(tipo) ? tipo : "";
        numFactura = !"none".equals(numFactura) ? numFactura : "";

        System.out.println(getDate(true,dateDesde));
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.busquedaMixtaTodosLosEstados(matricula, tipo, numFactura, getEstados(activo)[0], getEstados(activo)[1],getEstados(pago)[0], getEstados(pago)[1], getEstados(finalizacion)[0], getEstados(finalizacion)[1], getDate(true,dateDesde), getDate(false, dateHasta));
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }

    private  Date getDate(boolean isStartDate, String date){
        if(isStartDate && date.equals("none")){
            return  new Date(Long.MIN_VALUE);
        }

        if(!date.equals("none")) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return  new Date(System.currentTimeMillis());

    }

    private  boolean[] getEstados(String data){
        boolean respuesta[] = new boolean[2];
        if(data.equals("none")){
            respuesta[0] = true; respuesta[1] = false;
        }else  if("true".equals(data)){
            respuesta[0] = true; respuesta[1] = true;
        }else {
            respuesta[0] = false; respuesta[1] = false;
        }
        return respuesta;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioMantenimientoDto> findByTiposServiciosNombre(String nombre) {
        Optional<List<ServicioMantenimiento>> result = servicioMantenimientoRepository.findByTiposServiciosIdNombreContaining(nombre);
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

    @Override
    public List<ServicioMantenimientoDto> findByIdUsingListParam(List<Long> idList) {
        List<ServicioMantenimiento> result = servicioMantenimientoRepository.findByIdUsingListParam(idList);
        if (!result.isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result, ServicioMantenimientoDto.class);
        }
        return new ArrayList();
    }
}
