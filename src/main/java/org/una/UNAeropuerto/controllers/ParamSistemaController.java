package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.ParamSistemaDto;
import org.una.UNAeropuerto.services.IParamSistemaService;

@RestController
@RequestMapping("/param_sistema")
@Api(tags = {"Param Sistema"})
public class ParamSistemaController {
    @Autowired
    private IParamSistemaService paramSistemaService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo param sistema basado en su Id", response = ParamSistemaDto.class, tags = "Param Sistema")
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
    public ResponseEntity<?> update(@RequestBody ParamSistemaDto paramSistema) {
        try {
            ParamSistemaDto result = paramSistemaService.update(paramSistema);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el Param Sistema)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
