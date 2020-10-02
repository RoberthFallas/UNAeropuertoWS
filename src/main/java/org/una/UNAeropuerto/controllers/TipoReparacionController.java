package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.TipoReparacionDto;
import org.una.UNAeropuerto.services.ITipoService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/tiposReparaciones")
@Api(tags = {"Tipos Reparaciones"})
public class TipoReparacionController {

    @Autowired
    private ITipoService tipoService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo tipo basado en su Id", response = TipoReparacionDto.class, tags = "Tipos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            TipoReparacionDto result = tipoService.getById(id);
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
    @ApiOperation(value = "Obtiene un solo tipo basado en su nombre", response = TipoReparacionDto.class, tags = "Tipos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            TipoReparacionDto result = tipoService.getByNombre(nombre);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByNomb/{param}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de tipos cuyo nombre coinscida parcial o totalmente con el parámetro.", response = TipoReparacionDto.class, tags = "Tipos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByNombreAproximado(@PathVariable(value = "param") String parametro) {
        try {
            List<TipoReparacionDto> result = tipoService.findByNombre(parametro);
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
    @ApiOperation(value = "Obtiene una lista de tipos basándose en su estado", response = TipoReparacionDto.class, tags = "Tipos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<TipoReparacionDto> result = tipoService.findByActivos(estado);
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
    public ResponseEntity<?> create(@RequestBody TipoReparacionDto tipo) {
        try {
            TipoReparacionDto result = tipoService.create(tipo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> update(@RequestBody TipoReparacionDto tipo) {
        try {
            TipoReparacionDto result = tipoService.update(tipo);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el área)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
