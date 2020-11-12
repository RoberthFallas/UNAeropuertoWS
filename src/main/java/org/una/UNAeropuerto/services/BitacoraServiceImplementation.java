/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.BitacoraDto;
import org.una.UNAeropuerto.entities.Bitacora;
import org.una.UNAeropuerto.repositories.IBitacoraRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class BitacoraServiceImplementation implements IBitacoraService {

    @Autowired
    private IBitacoraRepository bitacoraRepo;
    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findByTipoCambio(String tipo) {
        Optional<List<Bitacora>> result = bitacoraRepo.findByTipoCambioContaining(tipo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }
    @Override
    @Transactional(readOnly = true)
    public BitacoraDto getById(long id) {
        Optional<Bitacora> result = bitacoraRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), BitacoraDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findByUserId(long id) {
        Optional<List<Bitacora>> result = bitacoraRepo.findByusuariosIdId(id);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findByUserCedula(String cedula) {
        Optional<List<Bitacora>> result = bitacoraRepo.findByUsuariosIdCedula(cedula);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findByFechaBitacora(Date fecha) {
        Optional<List<Bitacora>> result = bitacoraRepo.findByFechaModificacion(fecha);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> findBetweenDates(Date startDate, Date endDate) {
        Optional<List<Bitacora>> result = bitacoraRepo.findByFechaModificacionBetween(startDate,endDate);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BitacoraDto> busquedaMixta(String accion, String nombre, String apellido, String cedula,  String dateInicio, String dateFin) {

        accion  = !"none".equals(accion) ? accion : "";
        nombre = !"none".equals(nombre)? nombre: "";
        cedula  = !"none".equals(cedula)? cedula: "";

        Optional<List<Bitacora>> result = bitacoraRepo.busquedaMixta(accion, nombre, apellido, cedula, getDate(true,dateInicio), getDate(false, dateFin));

        System.out.println(result.isEmpty());
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), BitacoraDto.class);
        }
        return new ArrayList();
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

    @Override
    @Transactional
    public BitacoraDto create(BitacoraDto bitacora) {
        Bitacora entityBita = MapperUtils.entityFromDto(bitacora, Bitacora.class);
        entityBita = bitacoraRepo.save(entityBita);
        return MapperUtils.DtoFromEntity(entityBita, BitacoraDto.class);
    }

}
