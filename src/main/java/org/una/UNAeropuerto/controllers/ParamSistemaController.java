package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.ParamSistemaDto;
import org.una.UNAeropuerto.services.IParamSistemaService;

@RestController
@RequestMapping("/param_sistema")
@Api(tags = {"Parámetros Sistema"})
public class ParamSistemaController {

    @Autowired
    private IParamSistemaService paramSistemaService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo parámetro sistema basado en su Id", response = ParamSistemaDto.class, tags = "Parámetros Sistema")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            ParamSistemaDto result = paramSistemaService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> create(@RequestBody ParamSistemaDto paramSistema) {
        try {
            ParamSistemaDto result = paramSistemaService.create(paramSistema);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> update(@RequestBody ParamSistemaDto paramSistema) {
        try {
            ParamSistemaDto result = paramSistemaService.update(paramSistema);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encontró el parámetro sistema)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
