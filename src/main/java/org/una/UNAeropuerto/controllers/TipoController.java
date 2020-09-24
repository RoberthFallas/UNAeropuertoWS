package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.UNAeropuerto.dto.TipoDto;
import org.una.UNAeropuerto.dto.TipoDto;
import org.una.UNAeropuerto.dto.TipoDto;
import org.una.UNAeropuerto.services.ITipoService;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@Api(tags = {"Tipos"})
public class TipoController {

    @Autowired
    private ITipoService tipoService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un solo tipo basado en su Id", response = TipoDto.class, tags = "Tipos")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        try {
            TipoDto result = tipoService.getById(id);
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
    @ApiOperation(value = "Obtiene un solo tipo basado en su nombre", response = TipoDto.class, tags = "Tipos")
    public ResponseEntity<?> getByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            TipoDto result = tipoService.getByNombre(nombre);
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
    @ApiOperation(value = "Obtiene una lista de tipos cuyo nombre coinscida parcial o totalmente con el parámetro.", response = TipoDto.class, tags = "Tipos")
    public ResponseEntity<?> findByNombreAproximado(@PathVariable(value = "param") String parametro) {
        try {
            List<TipoDto> result = tipoService.findByNombre(parametro);
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
    @ApiOperation(value = "Obtiene una lista de tipos basándose en su estado", response = TipoDto.class, tags = "Tipos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            List<TipoDto> result = tipoService.findByActivos(estado);
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
    public ResponseEntity<?> create(@RequestBody TipoDto tipo) {
        try {
            TipoDto result = tipoService.create(tipo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody TipoDto tipo) {
        try {
            TipoDto result = tipoService.update(tipo);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado (no se encuentró el área)", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
