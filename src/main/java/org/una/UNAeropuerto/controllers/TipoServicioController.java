package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.TipoServicioDto;
import org.una.UNAeropuerto.services.ITipoServicioService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/tipos_servicios")
@Api(tags = {"Tipos de Servicios"})
public class TipoServicioController {

    @Autowired
    private ITipoServicioService tipoServicioService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo Tipo Servicios basado en su Id", response = TipoServicioDto.class, tags = "Tipos de Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            TipoServicioDto result = tipoServicioService.getById(id);
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
    @ApiOperation(value = "Obtiene un solo Tipo Servicios basada en su nombre", response = TipoServicioDto.class, tags = "Tipos de Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            TipoServicioDto result = tipoServicioService.getByNombre(nombre);
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
    @ApiOperation(value = "Obtiene una lista de los Tipos de Servicios cuyo nombre coinsida parcial o totalmente con el parámetro.", response = TipoServicioDto.class, tags = "Tipos de Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByNombreAproximado(@PathVariable(value = "param") String parametro) {
        try {
            List<TipoServicioDto> result = tipoServicioService.findByNombreAproximado(parametro);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByDescripcion/{param}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Tipos de Servicios cuyo nombre coincida parcial o totalmente con el parámetro.", response = TipoServicioDto.class, tags = "Tipos de Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByNombreDescripcionAprox(@PathVariable(value = "param") String parametro) {
        try {
            List<TipoServicioDto> result = tipoServicioService.findByDescripcionAproximado(parametro);
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
    @ApiOperation(value = "Obtiene una lista de Tipos Servicios basándose en su estado", response = TipoServicioDto.class, tags = "Tipos de Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<TipoServicioDto> result = tipoServicioService.findByEstado(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping()
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de Servicios", response = TipoServicioDto.class, responseContainer = "List", tags = "Tipos de Servicios")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tipoServicioService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> create(@RequestBody TipoServicioDto tipoServicio) {
        try {
            TipoServicioDto result = tipoServicioService.create(tipoServicio);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> update(@RequestBody TipoServicioDto tipoServicio) {
        try {
            TipoServicioDto result = tipoServicioService.update(tipoServicio);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el Tipo Servicios)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
