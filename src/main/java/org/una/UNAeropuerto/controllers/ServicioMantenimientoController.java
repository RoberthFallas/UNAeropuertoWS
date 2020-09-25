package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.services.IServicioMantenimientoService;

import java.util.List;

@RestController
@RequestMapping("/servicios_mantenimientos")
@Api(tags = {"Servicios_Mantenimientos"})

public class ServicioMantenimientoController {

    @Autowired
    private IServicioMantenimientoService servicioMantenimientoService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo servicios Mantenimientos basado en su Id", response = ServicioMantenimientoDto.class, tags = "Servicios_Mantenimientos")
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

    @GetMapping("findByEstado/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en su estado", response = ServicioMantenimientoDto.class, tags = "Servicios_Mantenimientos")
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
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en su estado de pago", response = ServicioMantenimientoDto.class, tags = "Servicios_Mantenimientos")
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
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en su estado de de finalizacion", response = ServicioMantenimientoDto.class, tags = "Servicios_Mantenimientos")
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

    @GetMapping("findByEstadoAvionesId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en el id del avion", response = ServicioMantenimientoDto.class, tags = "Servicios_Mantenimientos")
    public ResponseEntity<?> findByAvionesId(@PathVariable(value = "id") long id) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByAvionesId(id);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstadoHangaresId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en el id del hangar", response = ServicioMantenimientoDto.class, tags = "Servicios_Mantenimientos")
    public ResponseEntity<?> findByHangarId(@PathVariable(value = "id") long id) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByHangaresId(id);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("findByTiposServiciosId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en el id del tipo de servicio", response = ServicioMantenimientoDto.class, tags = "Servicios_Mantenimientos")
    public ResponseEntity<?> findByTipoServicosId(@PathVariable(value = "id") long id) {
        try {
            List<ServicioMantenimientoDto> result = servicioMantenimientoService.findByTiposServiciosId(id);
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
