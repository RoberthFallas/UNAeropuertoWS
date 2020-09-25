package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.CobroDto;
import org.una.UNAeropuerto.services.ICobroService;

import java.util.List;

@RestController
@RequestMapping("/cobros")
@Api(tags = {"Cobros"})
public class Cobros {
    @Autowired
    private ICobroService cobroService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo cobro basada en su Id", response = CobroDto.class, tags = "Cobros")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            CobroDto result = cobroService.getById(id);
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
    @ApiOperation(value = "Obtiene una lista de cobros basándose en su estado", response = CobroDto.class, tags = "Cobros")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<CobroDto> result = cobroService.findByActivos(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByServiciosMantenimientoId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de cobros basándose en su id de servicio mantenimeinto", response = CobroDto.class, tags = "Cobros")
    public ResponseEntity<?> findByMantenimientoId(@PathVariable(value = "id") long id) {
        try {
            List<CobroDto> result = cobroService.findByServiciosMantenimientoId(id);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByMontoAproximado/{monto}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de cobros basandose en un monto aproximado", response = CobroDto.class, tags = "Cobros")
    public ResponseEntity<?> findByMontoAproximado(@PathVariable(value = "monto") long monto) {
        try {
            List<CobroDto> result = cobroService.findByMontoAproximado(monto);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByDetalleCobroAprox/{detalle}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de cobros basandose en un detalle aproximado", response = CobroDto.class, tags = "Cobros")
    public ResponseEntity<?> findByDetalleAproximado(@PathVariable(value = "detalle") String detalle) {
        try {
            List<CobroDto> result = cobroService.findByDetalleCobroAproximado(detalle);
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
    public ResponseEntity<?> create(@RequestBody CobroDto cobro) {
        try {
            CobroDto result = cobroService.create(cobro);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody CobroDto cobro) {
        try {
            CobroDto result = cobroService.update(cobro);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el cobro)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    

}
