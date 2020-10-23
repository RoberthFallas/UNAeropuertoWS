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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.RolUsuarioDto;
import org.una.UNAeropuerto.dto.UsuarioDto;
import org.una.UNAeropuerto.services.IRolUsuarioService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/rolesUsuarios")
@Api(tags = {"Roles Usuarios"})
public class RolUsuarioController {

    @Autowired
    private IRolUsuarioService rolUserService;

    @GetMapping("/findByUserId/{id}")
    @ResponseBody
    @ApiOperation(value = "Retorna lista de Roles_Usuario tomando como parámetro el Id del usuario al que pertenecen", response = UsuarioDto.class, tags = "Roles Usuarios")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") long id) {
        try {
            List<RolUsuarioDto> result = rolUserService.findByUsuarioId(id);
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
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> create(@RequestBody RolUsuarioDto rolUsuario) {
        try {
            RolUsuarioDto result = rolUserService.create(rolUsuario);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException IAE) {
            return new ResponseEntity<>(IAE, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/changeStateById/{id}/{state}")
    @ResponseBody
    @ApiOperation(value = "Cambia estado (Activo inactivo) de RolUsuaario especificado en id", response = UsuarioDto.class, tags = "Roles Usuarios")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> changeStateById(@PathVariable(value = "id") long id,
            @PathVariable(value = "state") boolean state) {
        try {
            RolUsuarioDto result = rolUserService.changeState(id, state);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontro RolUsuario con id indicado", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
