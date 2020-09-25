package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.DetalleServicioDto;
import org.una.UNAeropuerto.services.IDetalleServicioService;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/detalles_servicios")
@Api(tags = {"Detalles Servicios"})
public class DetalleServicioController {

    @Autowired
    private IDetalleServicioService detalleServicioService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo detalle Servicio basado en su Id", response = DetalleServicioDto.class, tags = "Detalles Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            DetalleServicioDto result = detalleServicioService.getById(id);
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
    @ApiOperation(value = "Obtiene una lista de detalles Servicios basándose en su estado", response = DetalleServicioDto.class, tags = "Detalles Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<DetalleServicioDto> result = detalleServicioService.findByActivos(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByTiposId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de detalles Servicios basándose de acuedo el id del tipo", response = DetalleServicioDto.class, tags = "Detalles Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByTiposId(@PathVariable(value = "id") long id) {
        try {
            List<DetalleServicioDto> result = detalleServicioService.findByTiposId(id);
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
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> create(@RequestBody DetalleServicioDto detalleServicio) {
        try {
            DetalleServicioDto result = detalleServicioService.create(detalleServicio);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> update(@RequestBody DetalleServicioDto detalleServicio) {
        try {
            DetalleServicioDto result = detalleServicioService.update(detalleServicio);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el área)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
