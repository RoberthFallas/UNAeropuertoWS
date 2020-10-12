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
import org.una.UNAeropuerto.dto.AlertaDto;
import org.una.UNAeropuerto.services.IAlertaService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/alertas")
@Api(tags = {"Alertas"})
public class AlertaController {

    @Autowired
    private IAlertaService alertaService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una sola alerta basada en su Id", response = AlertaDto.class, tags = "Alertas")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            AlertaDto result = alertaService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTipo/{tipo}")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    @ApiOperation(value = "Obtiene una lista de alertas basadas en su tipo.", response = AlertaDto.class, tags = "Alertas")
    public ResponseEntity<?> findByCedulaAproximada(@PathVariable(value = "tipo") Byte tipo) {
        try {
            List<AlertaDto> result = alertaService.findByTipo(tipo);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTitulo/{titulo}")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    @ApiOperation(value = "Obtiene una lista de alertas cuyo título coincida parcial o totalmente con el parámetro.", response = AlertaDto.class, tags = "Alertas")
    public ResponseEntity<?> findByTitulo(@PathVariable(value = "titulo") String titulo) {
        try {
            List<AlertaDto> result = alertaService.findByTitulo(titulo);
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
    public ResponseEntity<?> create(@RequestBody AlertaDto alert) {
        try {
            AlertaDto result = alertaService.create(alert);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> update(@RequestBody AlertaDto alert) {
        try {
            AlertaDto result = alertaService.update(alert);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encontró la alerta)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
