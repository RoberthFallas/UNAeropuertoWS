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
import org.una.UNAeropuerto.dto.LugarDto;
import org.una.UNAeropuerto.services.ILugarService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/lugares")
@Api(tags = {"Lugares"})
public class LugarController {

    @Autowired
    private ILugarService lugarService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo Lugar basado en su Id", response = LugarDto.class, tags = "Lugares")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            LugarDto result = lugarService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByNombre/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo Lugar basado en su nombre", response = LugarDto.class, tags = "Lugares")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            LugarDto result = lugarService.getByNombre(nombre);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByNombre/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Lugares cuyo nombre coinsida de manera total o parcial con el parámetro suministrado.", response = LugarDto.class, tags = "Lugares")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "nombre") String nombre) {
        try {
            List<LugarDto> result = lugarService.findByNombre(nombre);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstado/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Lugares basándose en su estado", response = LugarDto.class, tags = "Lugares")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        try {
            List<LugarDto> result = lugarService.findByEstado(estado);
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
    public ResponseEntity<?> create(@RequestBody LugarDto lugar) {
        try {
            LugarDto result = lugarService.create(lugar);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> update(@RequestBody LugarDto provedor) {
        try {
            LugarDto result = lugarService.update(provedor);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el lugar)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
