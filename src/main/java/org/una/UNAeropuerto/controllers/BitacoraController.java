/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.BitacoraDto;
import org.una.UNAeropuerto.services.IBitacoraService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/biatacoras")
@Api(tags = {"Bitácoras"})
public class BitacoraController {

    @Autowired
    private IBitacoraService bitacoraRepo;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un sola bitácora basada en su Id", response = BitacoraDto.class, tags = "Bitácoras")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            BitacoraDto result = bitacoraRepo.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByUserId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de bitácoras basada en el Id del usuario al que pertenece", response = BitacoraDto.class, tags = "Bitácoras")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')  or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getByUserId(@PathVariable(value = "id") long id) {
        try {
            List<BitacoraDto> result = bitacoraRepo.findByUserId(id);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByUserCedula/{cedula}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de bitácoras basada en el Id del usuario al que pertenece", response = BitacoraDto.class, tags = "Bitácoras")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')  or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getByUserId(@PathVariable(value = "cedula") String cedula) {
        try {
            List<BitacoraDto> result = bitacoraRepo.findByUserCedula(cedula);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByTipoCambio/{parametro}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de bitácoras basada en el Id del usuario al que pertenece", response = BitacoraDto.class, tags = "Bitácoras")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')  or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getByTipoCambio(@PathVariable(value = "parametro") String parametro) {
        try {
            List<BitacoraDto> result = bitacoraRepo.findByTipoCambio(parametro);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/filter/{accion}/{nombre}/{apellido}/{cedula}/{dateInicio}/{dateFinal}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de bitácoras según los filtros aplicados", response = BitacoraDto.class, tags = "Bitácoras")
    @PreAuthorize("hasAuthority('AUDITOR_SERVICIOS_AERONAVES') or hasAuthority('AUDITOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_MANTENIMIENTO_AEROPUERTO')  or hasAuthority('ADMINISTRADOR')"  )
    public ResponseEntity<?> filter(
            @PathVariable(value = "accion") String accion,
            @PathVariable(value = "nombre") String nombre,
            @PathVariable(value = "apellido") String apellido,
            @PathVariable(value = "cedula") String cedula,
            @PathVariable(value = "dateInicio") String dateInicio,
            @PathVariable(value = "dateFinal") String dateFinal)
    {
        try {
            List<BitacoraDto> result = bitacoraRepo.busquedaMixta(accion, nombre,apellido, cedula,dateInicio, dateFinal);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFecha/{fecha}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de bitácoras basada en su fecha de creación", response = BitacoraDto.class, tags = "Bitácoras")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByFecha(@PathVariable(value = "fecha") String fecha) {
        try {
            Date fFecha = java.sql.Date.valueOf(fecha);
            List<BitacoraDto> result = bitacoraRepo.findByFechaBitacora(fFecha);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBetweenFechas/{start}/{end}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de bitácoras que hayan sido creadas entre las fechas especificadas", response = BitacoraDto.class, tags = "Bitácoras")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')  or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findBetweenFechas(@PathVariable(value = "start")@DateTimeFormat(pattern = "yyyy-MM-dd")  Date start,
            @PathVariable(value = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        try {

            List<BitacoraDto> result = bitacoraRepo.findBetweenDates(start, end);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("/create")
    @ResponseBody
 //   @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> create(@RequestBody BitacoraDto bitacora) {
        try {
            BitacoraDto result = bitacoraRepo.create(bitacora);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
