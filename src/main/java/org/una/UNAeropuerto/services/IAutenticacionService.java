/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.AuthenticationRequest;
import org.una.UNAeropuerto.dto.AuthenticationResponse;

/**
 *
 * @author LordLalo
 */
public interface IAutenticacionService {

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
