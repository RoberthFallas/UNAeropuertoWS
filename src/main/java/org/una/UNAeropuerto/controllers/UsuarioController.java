/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDto.class, tags = "Usuarios")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            UsuarioDto result = userService.ocultarById(id);
            if (result == null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
