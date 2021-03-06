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
public class GastoReparacionDto {

    private Long id;
    private Date fechaRegistro;
    private Boolean estadoPago;
    private Long numeroContrato;
    private Integer duracion;
    private Integer periodicidad;
    private String observaciones;
    private Boolean activo;
    private Float monto;
    @Setter(AccessLevel.NONE)
    private AreaDto areasId;
    private TipoReparacionDto tiposId;
    private ProvedorDto provedoresId;

}
