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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.AerolineaDto;
import org.una.UNAeropuerto.services.IAerolineaService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/aerolineas")
@Api(tags = {"Aerolíneas"})
public class AerolineaController {

    @Autowired
    private IAerolineaService aeroService;

    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS')")
    @ApiOperation(value = "Obtiene una sola aerolínea basada en su ID", response = AerolineaDto.class, tags = "Aerolíneas")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            AerolineaDto result = aeroService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByNomb/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una sola aerolínea basada en su nombre", response = AerolineaDto.class, tags = "Aerolíneas")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS')")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            AerolineaDto result = aeroService.getByNombre(nombre);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNomb/{param}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de aerolíneas cuyo nombre coincida parcial o totalmente con el parámetro.", response = AerolineaDto.class, tags = "Aerolíneas")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "param") String parametro) {
        try {
            List<AerolineaDto> result = aeroService.findByNombre(!"none".equals(parametro) ? parametro.replace("-", " ") : "");
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstado/{state}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de aerolíneas cuyo estado coincida con el parámetro.", response = AerolineaDto.class, tags = "Aerolíneas")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "state") Boolean state) {
        try {
            List<AerolineaDto> result = aeroService.findByEstado(state);
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
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> create(@RequestBody AerolineaDto aerolinea) {
        try {
            AerolineaDto result = aeroService.create(aerolinea);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> update(@RequestBody AerolineaDto aerolinea) {
        try {
            AerolineaDto result = aeroService.update(aerolinea);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró la Aerolínea)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/countVuelos/{aerolineaId}")
    @ResponseBody
    @ApiOperation(value = "Obtiene la cantidad de vulos registrados por la aerolinea especificada..", response = Long.class, tags = "Aerolíneas")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS')")
    public ResponseEntity<?> countVuelos(@PathVariable(value = "aerolineaId") Long aeroId) {
        try {
            Long count = aeroService.getCountVuelos(aeroId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
