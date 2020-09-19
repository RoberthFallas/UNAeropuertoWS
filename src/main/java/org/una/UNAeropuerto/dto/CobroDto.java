/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.dto;

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
public class CobroDto {

    private Long id;
    private Float monto;
    private String detalleCobro;
    private Boolean activo;
    @Setter(AccessLevel.NONE)
    private ServicioMantenimientoDto serviciosMantenimientoId;
}
