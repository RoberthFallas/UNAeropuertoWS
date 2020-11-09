/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.UNAeropuerto.dto.UsuarioDto;
import org.una.UNAeropuerto.entities.RolUsuario;
import org.una.UNAeropuerto.entities.Usuario;
import org.una.UNAeropuerto.jwt.JwtProvider;
import org.una.UNAeropuerto.repositories.IUsuarioRepository;
import org.una.UNAeropuerto.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class UsuarioServiceImplementation implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuarioRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getById(long id) {
        Optional<Usuario> result = userRepo.findById(id);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), UsuarioDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getByCedula(String cedula) {
        Optional<Usuario> result = userRepo.findByCedula(cedula);
        if (result.isPresent()) {
            return MapperUtils.DtoFromEntity(result.get(), UsuarioDto.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> findByCedulaAproximada(String parameter) {
        Optional<List<Usuario>> result = userRepo.findByCedulaContaining(parameter);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), UsuarioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> findByNombreAndApellidos(String nombre, String apellidos) {
        Optional<List<Usuario>> result = userRepo.findByNombreAndApellido(nombre, apellidos);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), UsuarioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional
    public UsuarioDto update(UsuarioDto usuario) {
        Optional<Usuario> result = userRepo.findById(usuario.getId());
        if (result.isPresent()) {
            Usuario entity = MapperUtils.entityFromDto(usuario, Usuario.class);
            if(!usuario.getContrasenna().isEmpty()) {
                entity.refreshContrasenna(result.get().getContrasenna());
                encodePassword(entity, usuario);
            }else {
                entity.setContrasenna(result.get().getContrasenna());
            }

            entity = userRepo.save(entity);
            return MapperUtils.DtoFromEntity(entity, UsuarioDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public UsuarioDto create(UsuarioDto usuario) {

        Usuario entityUser = MapperUtils.entityFromDto(usuario, Usuario.class);
        entityUser.refreshContrasenna(passwordEncoder.encode(usuario.getContrasenna()));
        entityUser = userRepo.save(entityUser);
        return MapperUtils.DtoFromEntity(entityUser, UsuarioDto.class);
    }

    @Override
    @Transactional
    public UsuarioDto ocultarById(long id) {
        Optional<Usuario> result = userRepo.findById(id);
        if (result.isPresent()) {
            Usuario entityUser = result.get();
            entityUser.setActivo(false);
            entityUser = userRepo.save(entityUser);
            return MapperUtils.DtoFromEntity(entityUser, UsuarioDto.class);
        }
        return null;
    }

    private void encodePassword(Usuario user, UsuarioDto userDto) {
        if (!userDto.getContrasenna().isBlank()) {
            if (user.getContrasenna() == null) {
                user.refreshContrasenna(passwordEncoder.encode(userDto.getContrasenna()));

                return;
            } else {
                if (!user.getContrasenna().equals(userDto.getContrasenna())) {
                    if (!passwordEncoder.matches(userDto.getContrasenna(), user.getContrasenna())) {
                        user.refreshContrasenna(passwordEncoder.encode(userDto.getContrasenna()));
                    }
                }
                return;
            }
        }
        throw new IllegalArgumentException("Contraseña no es válida o se encuntra vacía (no se pude encriptar)");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = userRepo.findByCedula(username);
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            for (RolUsuario r : usuario.getRolUsuarioList()) {
                roles.add(new SimpleGrantedAuthority(r.getRolesId().getNombre()));
            }
            UserDetails userDetails = new User(usuario.getCedula(), usuario.getContrasenna(), roles);
            return userDetails;
        } else {
            System.out.println( "usuario no es presente");
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> busquedaMixta(String nombre, String apellidos, String cedula,boolean activo) {
        Optional<List<Usuario>> result = userRepo.busquedaMixta(nombre, apellidos, cedula,activo);
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), UsuarioDto.class);
        }
        return new ArrayList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> busquedaMixtaConFecha(String nombre, String apellidos, String cedula,boolean activo, String fechaInicio, String fechaFinal) {
      

        Optional<List<Usuario>> result = userRepo.busquedaMixtaConFecha(nombre, apellidos, cedula,activo,Date.valueOf(fechaInicio),Date.valueOf(fechaFinal));
        if (result.isPresent()) {
            return MapperUtils.DtoListFromEntityList(result.get(), UsuarioDto.class);
        }
        return new ArrayList();

    }

}
