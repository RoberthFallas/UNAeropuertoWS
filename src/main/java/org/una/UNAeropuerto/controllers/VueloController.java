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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.VueloDto;
import org.una.UNAeropuerto.services.IVueloService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/vuelos")
@Api(tags = {"Vuelos"})
public class VueloController {

    @Autowired
    private IVueloService vueloService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo vuelo basado en su Id", response = VueloDto.class, tags = "Vuelos")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            VueloDto result = vueloService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByNombreVuelo/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo vuelo basado en su nombre", response = VueloDto.class, tags = "Vuelos")
    public ResponseEntity<?> getByNombreVuelo(@PathVariable(value = "nombre") String nombre) {
        try {
            VueloDto result = vueloService.getByNombreVuelo(nombre);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstado/{state}")
    @ResponseBody
    @ApiOperation(value = "Obtiene lista de vuelos según si estado.", response = VueloDto.class, tags = "Vuelos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "state") Byte state) {
        try {
            List<VueloDto> result = vueloService.findByEstado(state);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNombre/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de vuelos cuyo nobre coinsida parcial o totalmente con el parámetro.", response = VueloDto.class, tags = "Vuelos")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            List<VueloDto> result = vueloService.findByNombre(nombre);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEntreFechaYHoras/{start}/{end}")
    @ResponseBody
    @ApiOperation(value = "(Formato requerido 'yyyy-MM-dd HH:mm:ss').Obtiene una lista de vuelos cuyo día y hora coinsida con los parámetro sumistrado.", response = VueloDto.class, tags = "Vuelos")
    public ResponseEntity<?> findByEntreFechaYHoras(@PathVariable(value = "start") String start,
            @PathVariable(value = "end") String end) {
        try {
            Date fStart = java.sql.Timestamp.valueOf(start);
            Date fEnd = java.sql.Timestamp.valueOf(end);
            List<VueloDto> result = vueloService.findEntreFechaYHora(fStart, fEnd);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaVuelo/{fecha}")
    @ResponseBody
    @ApiOperation(value = "(Formato requerido 'yyyy-MM-dd').Obtiene una lista de vuelos que halla ocurrido o estén por ocurrir en la fecha suministrada", response = VueloDto.class, tags = "Vuelos")
    public ResponseEntity<?> findByFechaVuelo(@PathVariable(value = "fecha") String fecha) {
        try {
            Date fFecha = java.sql.Date.valueOf(fecha);
            List<VueloDto> result = vueloService.findByFechaVuelo(fFecha);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findEntreFechas/{start}/{end}")
    @ResponseBody
    @ApiOperation(value = "(Formato requerido 'yyyy-MM-dd').Obtiene una lista de vuelos que hallan ocurrido o estén por ocurrir en el lapso suministrado.", response = VueloDto.class, tags = "Vuelos")
    public ResponseEntity<?> findEntreFechas(@PathVariable(value = "start") String start,
            @PathVariable(value = "end") String end) {
        try {
            Date fStart = java.sql.Date.valueOf(start);
            Date fEnd = java.sql.Date.valueOf(end);
            List<VueloDto> result = vueloService.findEntreFechas(fStart, fEnd);
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
    public ResponseEntity<?> create(@RequestBody VueloDto vuelo) {
        try {
            VueloDto result = vueloService.create(vuelo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException IAE) {
            return new ResponseEntity<>(IAE, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody VueloDto vuelo) {
        try {
            VueloDto result = vueloService.update(vuelo);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el vuelo)", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException IAE) {
            return new ResponseEntity<>(IAE, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}