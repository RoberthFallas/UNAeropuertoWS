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
import org.una.UNAeropuerto.dto.DireccionDto;
import org.una.UNAeropuerto.services.IDireccionService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/Direcciones")
@Api(tags = {"Direcciones"})
public class DireccionController {

    @Autowired
    private IDireccionService direccionService;

    @GetMapping("findByDirecVuelo/{direc}")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    @ApiOperation(value = "Obtiene una lista de Direcciones de vuelo basadas en su direccion de curso (SLD)Salida, (LGD)Llegada.", response = DireccionDto.class, tags = "Direcciones")
    public ResponseEntity<?> getById(@PathVariable(value = "direc") String direc) {
        try {
            List<DireccionDto> result = direccionService.findByDireccionVuelo(direc);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByVueloId/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    @ApiOperation(value = "Obtiene una lista de Direcciones de vuelo basadas en el id del Lugar de al que se relaciona", response = DireccionDto.class, tags = "Direcciones")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        try {
            List<DireccionDto> result = direccionService.findByLugarId(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
