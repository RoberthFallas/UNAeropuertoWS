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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.RolDto;
import org.una.UNAeropuerto.services.IRolService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/roles")
@Api(tags = {"Roles"})
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo Rol basado en su Id", response = RolDto.class, tags = "Roles")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            RolDto result = rolService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByNomb/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo Rol basado en su Id", response = RolDto.class, tags = "Roles")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            RolDto result = rolService.getByNombre(nombre);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByDescrip/{param}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de roles cuya descrpción coinsida parcial o totalmente con el parámetro.", response = RolDto.class, tags = "Roles")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "param") String param) {
        try {
            List<RolDto> result = rolService.findByDescripcion(param);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByNombre/{param}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de roles cuyo nombre coinsida parcial o totalmente con el parámetro.", response = RolDto.class, tags = "Roles")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "param") String param) {
        try {
            List<RolDto> result = rolService.findByNombre(param);
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
    @ApiOperation(value = "Obtiene una lista de roles basándose en su estado", response = RolDto.class, tags = "Roles")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<RolDto> result = rolService.findByestado(estado);
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
    public ResponseEntity<?> create(@RequestBody RolDto rol) {
        try {
            RolDto result = rolService.create(rol);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody RolDto rol) {
        try {
            RolDto result = rolService.update(rol);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el rol)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
