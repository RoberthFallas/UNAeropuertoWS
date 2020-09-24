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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.PistaDto;
import org.una.UNAeropuerto.services.IPistaService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/pistas")
@Api(tags = {"Pistas"})
public class PistaController {

    @Autowired
    private IPistaService pistaService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una sola Pista basada en su Id", response = PistaDto.class, tags = "Pistas")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            PistaDto result = pistaService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByNombre/{numPista}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una sola Pista basada en su número de pista", response = PistaDto.class, tags = "Pistas")
    public ResponseEntity<?> getByNumeroPista(@PathVariable(value = "numPista") String numPista) {
        try {
            PistaDto result = pistaService.getByNumeroPista(numPista);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByNumeroPista/{numPista}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Lugares cuyo nombre coinsida de manera total o parcial con el parámetro suministrado.", response = PistaDto.class, tags = "Pistas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "numPista") String numPista) {
        try {
            List<PistaDto> result = pistaService.findByNumeroPista(numPista);
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
    @ApiOperation(value = "Obtiene una lista de Lugares cuyo nombre coinsida de manera total o parcial con el parámetro suministrado.", response = PistaDto.class, tags = "Pistas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") Boolean estado) {
        try {
            List<PistaDto> result = pistaService.findByEstado(estado);
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
    public ResponseEntity<?> create(@RequestBody PistaDto pista) {
        try {
            PistaDto result = pistaService.create(pista);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody PistaDto pista) {
        try {
            PistaDto result = pistaService.update(pista);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el la pista)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
