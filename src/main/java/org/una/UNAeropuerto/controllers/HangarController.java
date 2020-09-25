package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.HangarDto;
import org.una.UNAeropuerto.services.IHangarService;

import java.util.List;
@RestController
@RequestMapping("/hangares")
@Api(tags = {"Hangares"})
public class HangarController {

    @Autowired
    private IHangarService hangarService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo hangar basado en su Id", response = HangarDto.class, tags = "Hangares")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            HangarDto result = hangarService.getById(id);
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
    @ApiOperation(value = "Obtiene una lista de hangares basándose en su estado", response = HangarDto.class, tags = "Hangares")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<HangarDto> result = hangarService.findByEstado(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
                
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEspecialidadAprox/{especialidad}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de hangares basándose en su estado", response = HangarDto.class, tags = "Hangares")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "especialidad") String especialidad) {
        try {
            List<HangarDto> result = hangarService.findByEspecialidadAproximado(especialidad);
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
    public ResponseEntity<?> create(@RequestBody HangarDto hangar) {
        try {
            HangarDto result = hangarService.create(hangar);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody HangarDto hangar) {
        try {
            HangarDto result = hangarService.update(hangar);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el área)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
