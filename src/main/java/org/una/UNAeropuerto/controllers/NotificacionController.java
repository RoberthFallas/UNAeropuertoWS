package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.NotificacionDto;
import org.una.UNAeropuerto.services.INotificacionService;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@Api(tags = {"Notificaciones"})
public class NotificacionController {

    @Autowired
    private INotificacionService notificacionService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo notificacion basado en su Id", response = NotificacionDto.class, tags = "Notificaciones")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            NotificacionDto result = notificacionService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstado/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de notificacions basándose en su estado", response = NotificacionDto.class, tags = "Notificaciones")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<NotificacionDto> result = notificacionService.findByActivos(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByAreasId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de notificacions basándose en el área id", response = NotificacionDto.class, tags = "Notificaciones")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "id") long id) {
        try {
            List<NotificacionDto> result = notificacionService.findByAreaId(id);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("buscarIdandEstado/{id}/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de notificacions basándose en el área id", response = NotificacionDto.class, tags = "Notificaciones")
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO') or hasAuthority('GERENTE_SERVICIOS_AERONAVES') or hasAuthority('GERENTE_MANTENIMIENTO_AEROPUERTO')"
            + " or hasAuthority('AUDITOR_CONTROL_VUELOS')")
    public ResponseEntity<?> buscarIdandEstado(@PathVariable(value = "id") long id, @PathVariable(value = "estado") boolean estado) {
        try {
            List<NotificacionDto> result = notificacionService.buscarAreaAndestado(id, estado);
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
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> create(@RequestBody NotificacionDto notificacion) {
        try {
            NotificacionDto result = notificacionService.create(notificacion);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GERENTE_CONTROL_VUELO') or hasAuthority('GERENTE_SERVICIOS_AERONAVES') or hasAuthority('GERENTE_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> update(@RequestBody NotificacionDto notificacionDto) {
        try {
            NotificacionDto result = notificacionService.upDate(notificacionDto);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encontró el lugar)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
