package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.AvionDto;
import org.una.UNAeropuerto.services.IAvionService;
import java.util.List;

@RestController
@RequestMapping("/aviones")
@Api(tags = {"Aviones"})
public class AvionController {

    @Autowired
    private IAvionService avionService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo avion basada en su Id", response = AvionDto.class, tags = "Aviones")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            AvionDto result = avionService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByMatricula/{matricula}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo avion basado en su matricula", response = AvionDto.class, tags = "Aviones")
    public ResponseEntity<?> getByMatricula(@PathVariable(value = "matricula") String matricula) {
        try {
            AvionDto result = avionService.getByMatricula(matricula);
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
    @ApiOperation(value = "Obtiene una lista de aviones basándose en su estado", response = AvionDto.class, tags = "Aviones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<AvionDto> result = avionService.findByEstado(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByAerolineaId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de aviones basándose en su aerolinea", response = AvionDto.class, tags = "Aviones")
    public ResponseEntity<?> findByAerolineaId(@PathVariable(value = "id") long id) {
        try {
            List<AvionDto> result = avionService.findByAerolineaId(id);
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
    public ResponseEntity<?> create(@RequestBody AvionDto avion) {
        try {
            AvionDto result = avionService.create(avion);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody AvionDto avion) {
        try {
            AvionDto result = avionService.update(avion);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el avion)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
