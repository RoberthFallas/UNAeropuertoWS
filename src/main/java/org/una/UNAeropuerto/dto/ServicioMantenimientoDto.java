/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.dto;

import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Roberth :)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServicioMantenimientoDto {

    private Long id;
    private Date fechaServicio;
    private Long numeroFactura;
    private Boolean estadoPago;
    private Boolean estaFinalizacion;
    private Boolean activo;
    @Setter(AccessLevel.NONE)
    private AvionDto avionesId;
    @Setter(AccessLevel.NONE)
    private HangarDto hangaresId;
    @Setter(AccessLevel.NONE)
    private TipoServicioDto tiposServiciosId;
    @Setter(AccessLevel.NONE)
    private List<CobroDto> cobroList;
}
