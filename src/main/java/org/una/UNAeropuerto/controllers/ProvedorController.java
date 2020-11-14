package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.ProvedorDto;
import org.una.UNAeropuerto.services.IProvedorService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/provedores")
@Api(tags = {"Provedores"})
public class ProvedorController {

    @Autowired
    private IProvedorService provedorService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo provedor basado en su Id", response = ProvedorDto.class, tags = "Provedores")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            ProvedorDto result = provedorService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByNombre/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene proveedores basado en un  nombre", response = ProvedorDto.class, tags = "Provedores")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            List<ProvedorDto> result = provedorService.getByNombre(nombre);
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
    @ApiOperation(value = "Obtiene una lista de provedors basándose en su estado", response = ProvedorDto.class, tags = "Provedores")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')  or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<ProvedorDto> result = provedorService.findByActivos(estado);
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
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> create(@RequestBody ProvedorDto provedor) {
        try {
            ProvedorDto result = provedorService.create(provedor);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> update(@RequestBody ProvedorDto provedor) {
        try {
            ProvedorDto result = provedorService.update(provedor);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encontró el provedor)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
