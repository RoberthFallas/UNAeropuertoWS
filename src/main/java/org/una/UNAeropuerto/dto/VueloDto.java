/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.dto;

import java.util.Date;
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
public class VueloDto {

    private Long id;
    private String nombreVuelo;
    private Date horaSalida;
    private Date horaLlegada;
    private Byte estado;
    @Setter(AccessLevel.NONE)
    private AvionDto avionesId;
    @Setter(AccessLevel.NONE)
    private PistaDto pistasId;
    @Setter(AccessLevel.NONE)
    private LugarDto lugarSalida;
    @Setter(AccessLevel.NONE)
    private LugarDto lugarLlegada;
    @Setter(AccessLevel.NONE)
    private AlertaDto alerta;
}
