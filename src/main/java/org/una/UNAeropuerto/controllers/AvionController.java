package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.AvionDto;
import org.una.UNAeropuerto.services.IAvionService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/aviones")
@Api(tags = {"Aviones"})
public class AvionController {

    @Autowired
    private IAvionService avionService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo avión basado en su Id", response = AvionDto.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or  hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('AUDITOR_CONTROL_VUELOS') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            AvionDto result = avionService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByMatricula/{matricula}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo avión basado en su matrícula", response = AvionDto.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or  hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('AUDITOR_CONTROL_VUELOS') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> getByMatricula(@PathVariable(value = "matricula") String matricula) {
        try {
            AvionDto result = avionService.getByMatricula(matricula);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstado/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de aviones basándose en su estado", response = AvionDto.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or hasAuthority('AUDITOR_CONTROL_VUELOS') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<AvionDto> result = avionService.findByEstado(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByMatriculaLike/{matricula}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de aviones que coincinal parcialmente con el valor", response = AvionDto.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or  hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('AUDITOR_CONTROL_VUELOS')  or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByMatriculaLike(@PathVariable(value = "matricula") String matricula) {
        try {
            List<AvionDto> result = avionService.getByMatriculaLike(matricula);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByAerolineaNombre/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de aviones basándose en su aerolínea", response = AvionDto.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or  hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('AUDITOR_CONTROL_VUELOS')or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> findByAerolineaNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            List<AvionDto> result = avionService.findByAerolineaNombre(nombre);
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
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> create(@RequestBody AvionDto avion) {
        try {
            AvionDto result = avionService.create(avion);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS')")
    public ResponseEntity<?> update(@RequestBody AvionDto avion) {
        try {
            AvionDto result = avionService.update(avion);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encontró el avión)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("filter/{matricula}/{aerolinea}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de aviones basándose en su matricula y aerolinea", response = AvionDto.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('GESTOR_CONTROL_VUELOS') or  hasAuthority('GESTOR_SERVICIOS_AERONAVES') or hasAuthority('AUDITOR_CONTROL_VUELOS')"
            + " or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> filter(@PathVariable(value = "matricula") String matricula, @PathVariable(value = "aerolinea") String aerolinea) {
        try {
            matricula = matricula.replace("-", " ");
            aerolinea = aerolinea.replace("-", " ");
            List<AvionDto> result = avionService.filter(!"none".equals(matricula) ? matricula : "", !"none".equals(aerolinea) ? aerolinea : "");
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
