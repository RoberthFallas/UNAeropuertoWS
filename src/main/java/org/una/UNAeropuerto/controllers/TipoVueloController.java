/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.TipoVueloDto;
import org.una.UNAeropuerto.services.ITipoVueloService;

/**
 *
 * @author roberth :)
 */
@RestController
@RequestMapping("/tipos_vuelos")
@Api(tags = {"Tipos de vuelos"})
public class TipoVueloController {

    @Autowired
    private ITipoVueloService tipoVueloService;

    @GetMapping("/findByEstado/{state}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un una lista de Tipos de vuelos basado en su estado", response = TipoVueloDto.class, tags = "Tipos de vuelos")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS')  or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findAll(@PathVariable(value = "state") boolean state) {
        try {
            List<TipoVueloDto> result = tipoVueloService.findByEstado(state);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation(value = "Obtiene un una lista con todos los tipos de vuelos registrados", response = TipoVueloDto.class, tags = "Tipos de vuelos")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS')")
    public ResponseEntity<?> getById() {
        try {
            List<TipoVueloDto> result = tipoVueloService.findAll();
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
