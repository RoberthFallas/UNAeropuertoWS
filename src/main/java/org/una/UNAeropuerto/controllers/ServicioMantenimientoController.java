package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.services.IServicioMantenimientoService;

import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/servicios_mantenimientos")
@Api(tags = {"Servicios de Mantenimiento"})

public class ServicioMantenimientoController {

    @Autowired
    private IServicioMantenimientoService servicioMantenimientoService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo Servicios Mantenimientos basado en su Id", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            ServicioMantenimientoDto result = servicioMantenimientoService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("findByNumeroFactura/{numero}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en un numero de factura", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByNumeroFactura(@PathVariable(value = "numero") Long numero) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.getByNumeroFactura(numero);
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
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en su estado", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByEstado(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstadoPago/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en su estado de pago", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByEstadoPago(@PathVariable(value = "estado") boolean estado) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByEstadoPago(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstadoFinalizacion/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en su estado de de finalizacion", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByEstadoFinalizacion(@PathVariable(value = "estado") boolean estado) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByEstadoFinalizacion(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstadoAvionesMatricula/{matricula}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en el id del avion", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByAvionesMatricula(@PathVariable(value = "matricula") String matricula) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByAvionesMatricula(matricula);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("findEntreFechas/{fechaInicio}/{fechaFinal}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en el id del avion", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findEntreFechas(@PathVariable(value = "fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date fechaInicio, @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date fechaFinal){
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByFechaServicioBetween(fechaInicio, fechaFinal);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("findByTiposServiciosNombre/{nombre}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en el id del tipo de servicio", response = ServicioMantenimientoDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> findByTipoServicosNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByTiposServiciosNombre(nombre);
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
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> create(@RequestBody ServicioMantenimientoDto servicioMantenimiento) {
        try {
            ServicioMantenimientoDto result = servicioMantenimientoService.create(servicioMantenimiento);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> update(@RequestBody ServicioMantenimientoDto servicioMantenimiento) {
        try {
            ServicioMantenimientoDto result = servicioMantenimientoService.update(servicioMantenimiento);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el Servicio Mantenimiento)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
