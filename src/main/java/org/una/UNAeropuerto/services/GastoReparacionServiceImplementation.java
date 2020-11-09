package org.una.UNAeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.GastoReparacionDto;
import org.una.UNAeropuerto.entities.GastoReparacion;
import org.una.UNAeropuerto.repositories.IGastoReparacionRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GastoReparacionServiceImplementation implements IGastoReparacionService {

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
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByActivo(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastoReparacionDto> findByEstadoPago(boolean estado) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByEstadoPago(estado);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastoReparacionDto> findByAreaId(long id) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByAreasIdId(id);
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
    public List<GastoReparacionDto> findBetweenDiasPeriocidad(Integer start, Integer end) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByPeriodicidadBetween(start, end);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    public List<GastoReparacionDto> findBweteenDiasDuracion(Integer start, Integer end) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByDuracionBetween(start, end);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastoReparacionDto> busquedaMixta(String numContrato, String tipo, String proveedor, String activo, String pago, String startDate, String endDate, String startDuracion, String endDuracion, String startPeriocidad, String endPeriocidad) {
        numContrato  = !"none".equals(numContrato) ? numContrato : "";
        tipo = !"none".equals(tipo) ? tipo : "";
        proveedor = !"none".equals(proveedor) ? proveedor : "";

        Optional<List<GastoReparacion>> result = gastoReparacionRepository.busquedaMixtaTodosLosEstados(numContrato, tipo,proveedor, getEstados(activo)[0],getEstados(activo)[1], getEstados(pago)[0],getEstados(pago)[1] ,getDate(true, startDate), getDate(false, endDate),getNum(true, startDuracion), getNum(false, endDuracion),getNum(true, startPeriocidad), getNum(false, endPeriocidad));
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), GastoReparacionDto.class);
        }
        return new ArrayList();
    }

    private  int getNum(boolean isStartNum , String num){
        System.out.println("is in num");
        if(num.equals("none") && isStartNum) return 0;
        else if(num.equals("none") && !isStartNum) return Integer.MAX_VALUE;
        else {
            try {
               return Integer.parseInt(num);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;

    }
    private Date getDate(boolean isStartDate, String date) {
        if (isStartDate && date.equals("none")) {
            return new Date(Long.MIN_VALUE);
        }

        if (!date.equals("none")) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return new Date(System.currentTimeMillis());

    }

    private boolean[] getEstados(String data) {
        boolean respuesta[] = new boolean[2];
        if (data.equals("none")) {
            respuesta[0] = true;
            respuesta[1] = false;
        } else if ("true".equals(data)) {
            respuesta[0] = true;
            respuesta[1] = true;
        } else {
            respuesta[0] = false;
            respuesta[1] = false;
        }
        return respuesta;
    }

    @Override
    public List<GastoReparacionDto> findByTipoNombre(String nombre) {
        Optional<List<GastoReparacion>> result = gastoReparacionRepository.findByTiposIdNombreContaining(nombre);
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
    public GastoReparacionDto create(GastoReparacionDto gastoReparacionDto) {
        GastoReparacion entityUser = MapperUtils.entityFromDto(gastoReparacionDto, GastoReparacion.class);
        entityUser = gastoReparacionRepository.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, GastoReparacionDto.class);
    }

    @Override
    public List<GastoReparacionDto> findByIdUsingListParam(List<Long> idList) {
        List<GastoReparacion> result = gastoReparacionRepository.findByIdUsingListParam(idList);
        if (!result.isEmpty()) {
            return MapperUtils.DtoListFromEntityList(result, GastoReparacionDto.class);
        }
        return new ArrayList();
    }
}
