/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.AuthenticationRequest;
import org.una.UNAeropuerto.dto.AuthenticationResponse;
import org.una.UNAeropuerto.dto.RolUsuarioDto;
import org.una.UNAeropuerto.dto.UsuarioDto;
import org.una.UNAeropuerto.jwt.JwtProvider;
import org.una.UNAeropuerto.repositories.IUsuarioRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class AutenticacionServiceImplementation implements IAutenticacionService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UsuarioServiceImplementation usuarioServiceImplementation;

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        //   Optional<Usuario> usuario = findByCedula(authenticationRequest.getCedula());
        UsuarioDto usuario = usuarioServiceImplementation.getByCedula(authenticationRequest.getCedula());
//        if (usuario.isPresent()) {
        if (usuario != null) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            //UsuarioDto usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDto.class);
            authenticationResponse.setUsuario(usuario);
            List<RolUsuarioDto> rolUsuarioDto= MapperUtils.DtoListFromEntityList(usuario.getRolUsuarioList(), RolUsuarioDto.class);
            authenticationResponse.setRolUsuario(rolUsuarioDto);
            return authenticationResponse;
        } else {
            return null;
        }

    }

}
