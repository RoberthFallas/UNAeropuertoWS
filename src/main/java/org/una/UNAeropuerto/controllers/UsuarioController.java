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
import org.una.UNAeropuerto.dto.UsuarioDto;
import org.una.UNAeropuerto.services.IUsuarioService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService userService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo usuario basado en su Id", response = UsuarioDto.class, tags = "Usuarios")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            UsuarioDto result = userService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByCedula/{ced}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo usuario basado en su número de cédula", response = UsuarioDto.class, tags = "Usuarios")
    public ResponseEntity<?> getByCedula(@PathVariable(value = "ced") String cedula) {
        try {
            UsuarioDto result = userService.getByCedula(cedula);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByCed/{param}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de usuarios cuya cédula coinsida parcial o totalmente con el parámetro.", response = UsuarioDto.class, tags = "Usuarios")
    public ResponseEntity<?> findByCedulaAproximada(@PathVariable(value = "param") String parametro) {
        try {
            List<UsuarioDto> result = userService.findByCedulaAproximada(parametro);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByNombAndApell/{nomb}/{apell}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de usuarios cuyo nombre completo coinsida parcial o totalmente con el parámetro.", response = UsuarioDto.class, tags = "Usuarios")
    public ResponseEntity<?> findByNameAndApellidos(@PathVariable(value = "nomb") String nombre,
             @PathVariable(value = "apell") String apellidos) {
        try {
            List<UsuarioDto> result = userService.findByNombreAndApellidos(nombre, apellidos);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("hideById/{id}")
    @ResponseBody
    @ApiOperation(value = "Oculta del sistema a un usuario, basándose en su Id.", response = UsuarioDto.class, tags = "Usuarios")
    public ResponseEntity<?> hideById(@PathVariable(value = "id") long id) {
        try {
            UsuarioDto result = userService.ocultarById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("El usuario indicado no está en la base de datos (Id inválido)", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuario) {
        try {
            UsuarioDto result = userService.create(usuario);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException IAE) {
            return new ResponseEntity<>(IAE, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuario) {
        try {
            UsuarioDto result = userService.update(usuario);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el usuario)", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException IAE) {
            return new ResponseEntity<>(IAE, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
