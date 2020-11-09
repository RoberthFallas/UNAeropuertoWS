package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.GastoReparacionDto;
import org.una.UNAeropuerto.dto.ServicioMantenimientoDto;
import org.una.UNAeropuerto.dto.VueloDto;
import org.una.UNAeropuerto.entities.GastoReparacion;
import org.una.UNAeropuerto.services.IGastoReparacionService;

import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/gastos_reparaciones")
@Api(tags = {"Gastos Reparaciones"})
public class GastoReparacionController {

    @Autowired
    private IGastoReparacionService gastoReparacionService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo gasto reparación basado en su Id", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO') or hasAuthority('GERENTE_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            GastoReparacionDto result = gastoReparacionService.getById(id);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByNumeroContrato/{numeroContrato}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo gasto reparación basado en su número de contrato", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> getByNumeroContrato(@PathVariable(value = "numeroContrato") long numeroContrato) {
        try {
            GastoReparacionDto result = gastoReparacionService.getByNumeroContrato(numeroContrato);
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
    @ApiOperation(value = "Obtiene una lista de gastos reparaciones basándose en su estado", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO') or hasAuthority('GERENTE_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByEstado(estado);
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
    @ApiOperation(value = "Obtiene una lista de gastos reparaciones basándose en su estado de pago", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByEstadoPago(@PathVariable(value = "estado") boolean estado) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByEstadoPago(estado);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByAreaId/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de gastos reparaciones basándose en su área según id", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByAreaId(@PathVariable(value = "id") long id) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByAreaId(id);
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
    public ResponseEntity<?> create(@RequestBody GastoReparacionDto gastoReparacion) {
        try {
            GastoReparacionDto result = gastoReparacionService.create(gastoReparacion);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO') or hasAuthority('GERENTE_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> update(@RequestBody GastoReparacionDto gastoReparacion) {
        try {
            GastoReparacionDto result = gastoReparacionService.update(gastoReparacion);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encontró el gasto reparación)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findEntreDiasPeriocidad/{inicio}/{fin}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos entre dias de periocidad", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findEntreDiasPeriocidad(@PathVariable(value = "inicio") Integer inicio, @PathVariable(value = "fin") Integer fin) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findBetweenDiasPeriocidad(inicio, fin);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findEntreDiasDuracion/{inicio}/{fin}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos entre dias de periocidad", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findEntreDiasDuracion(@PathVariable(value = "inicio") Integer inicio, @PathVariable(value = "fin") Integer fin) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findBweteenDiasDuracion(inicio, fin);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByTipoReparacionNombre/{parametro}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos según el tipo de reparación", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findEntreDiasDuracion(@PathVariable(value = "parametro") String parametro) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByTipoNombre(parametro);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{numeroContrato}/{tipo}/{proveedor}/{activo}/{pago}/{dateDesde}/{dateHasta}/{duracionDesde}/{duracionHasta}/{periocidadDesde}/{periocidadHasta}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de servicios mantenimientos filtrados por medio de los parámetros suministrados", response = GastoReparacionDto.class, tags = "Gastos Reparaciones")
    @PreAuthorize("hasAuthority('GESTOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> filter(
            @PathVariable(value = "numeroContrato") String numeroContrato,
            @PathVariable(value = "tipo") String tipo,
            @PathVariable(value = "proveedor") String proveedor,
            @PathVariable(value = "activo") String activo,
            @PathVariable(value = "pago") String pago,
            @PathVariable(value = "dateDesde") String dateDesde,
            @PathVariable(value = "dateHasta") String dateHasta,
            @PathVariable(value = "duracionDesde") String duracionDesde,
            @PathVariable(value = "duracionHasta") String duracionHasta,
            @PathVariable(value = "periocidadDesde") String periocidadDesde,
            @PathVariable(value = "periocidadHasta") String periocidadHasta

    ) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.busquedaMixta(numeroContrato, tipo, proveedor, activo, pago, dateDesde, dateHasta, duracionDesde, duracionHasta,periocidadDesde, periocidadHasta);
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
    @ApiOperation(value = "Obtiene una lista de Servicio Mantenimientos basándose en el id del avion", response = GastoReparacionDto.class, tags = "Servicios de Mantenimiento")
    @PreAuthorize("hasAuthority('GESTOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findEntreFechas(@PathVariable(value = "fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByFechaRegistroBetween(fechaInicio, fechaFinal);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/findByIdUsingListParam")
    @ResponseBody
    @ApiOperation(value = "Recibe una lista de valores long. Retorna los vuelos cuyo Id se halle dentro de la lista", response = GastoReparacionDto.class, tags = "Vuelos")
    @PreAuthorize("hasAuthority('GERENTE_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> findByIdUsingListParam(@RequestBody List<Long> idList) {
        try {
            List<GastoReparacionDto> result = gastoReparacionService.findByIdUsingListParam(idList);
            if (!result.isEmpty()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("Sin resultados", HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
