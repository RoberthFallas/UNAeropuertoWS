/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.dto.AuthenticationRequest;
import org.una.UNAeropuerto.dto.AuthenticationResponse;
import org.una.UNAeropuerto.dto.UsuarioDto;
import org.una.UNAeropuerto.services.IAutenticacionService;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/autenticacion")
@Api(tags = {"Autenticación"})
public class AutenticacionController {

    @Autowired
    private IAutenticacionService autenticacionService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDto.class, tags = "Autenticación")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            AuthenticationResponse token = autenticacionService.login(authenticationRequest);
            if (token != null) {
                authenticationResponse = token;
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales inválidos", HttpStatus.UNAUTHORIZED);
            }
        } catch (InternalAuthenticationServiceException | BadCredentialsException AutEx) {
            return new ResponseEntity<>(AutEx, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
