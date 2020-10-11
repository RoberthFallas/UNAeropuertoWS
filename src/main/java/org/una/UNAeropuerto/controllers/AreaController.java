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
import org.una.UNAeropuerto.dto.AreaDto;
import org.una.UNAeropuerto.services.IAreaService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/areas")
@Api(tags = {"Areas"})
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo área basada en su Id", response = AreaDto.class, tags = "Areas")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            AreaDto result = areaService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByNombre/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo area basado en su nombre", response = AreaDto.class, tags = "Areas")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            AreaDto result = areaService.getByNombre(nombre);
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
    @ApiOperation(value = "Obtiene una lista de áreas cuyo nombre coinsida parcial o totalmente con el parámetro.", response = AreaDto.class, tags = "Areas")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> findByCedulaAproximada(@PathVariable(value = "param") String parametro) {
        try {
            List<AreaDto> result = areaService.findByNombre(parametro);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDescrip/{param}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de áreas cuya descripcion coinsida parcial o totalmente con el parámetro.", response = AreaDto.class, tags = "Areas")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> findByDescripAprox(@PathVariable(value = "param") String parametro) {
        try {
            List<AreaDto> result = areaService.findByDescripcion(parametro);
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
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> create(@RequestBody AreaDto area) {
        try {
            AreaDto result = areaService.create(area);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO')")
    public ResponseEntity<?> update(@RequestBody AreaDto area) {
        try {
            AreaDto result = areaService.update(area);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el área)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
