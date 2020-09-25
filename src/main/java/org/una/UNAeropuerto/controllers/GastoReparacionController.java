package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.GastoReparacionDto;
import org.una.UNAeropuerto.services.IGastoReparacionService;

import java.util.List;

@RestController
@RequestMapping("/gastos_reparaciones")
@Api(tags = {"Gastos Reparaciones"})
public class GastoReparacionController {

    @Autowired
    private IGastoReparacionService gastoReparacionService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo gasto reparacion basado en su Id", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            GastoReparacionDto result = gastoReparacionService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByNumeroContrato/{numeroContrato}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo gasto reparacion basado en su numero de contrato", response = GastoReparacionDto.class, tags =  "Gastos Reparaciones")
    public ResponseEntity<?> getByNumeroContrato(@PathVariable(value = "numeroContrato") long numeroContrato) {
        try {
            GastoReparacionDto result = gastoReparacionService.getByNumeroContrato(numeroContrato);
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
    @ApiOperation(value = "Obtiene una lista de Gastos Reparaciones basándose en su estado", response = GastoReparacionDto.class, tags =  "Gastos Reparaciones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByEstado(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstadoPago/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Gastos Reparaciones basándose en su estado de pago", response = GastoReparacionDto.class, tags =  "Gastos Reparaciones")
    public ResponseEntity<?> findByEstadoPago(@PathVariable(value = "estado") boolean estado) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByEstadoPago(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByAreaId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Gastos Reparaciones basándose en su area según id", response = GastoReparacionDto.class, tags =  "Gastos Reparaciones")
    public ResponseEntity<?> findByAreaId(@PathVariable(value = "id") long id) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByAreaId(id);
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
    public ResponseEntity<?> create(@RequestBody GastoReparacionDto gastoReparacion) {
        try {
            GastoReparacionDto result = gastoReparacionService.create(gastoReparacion);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody GastoReparacionDto gastoReparacion) {
        try {
            GastoReparacionDto result = gastoReparacionService.update(gastoReparacion);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el área)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
