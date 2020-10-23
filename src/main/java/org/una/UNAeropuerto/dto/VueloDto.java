/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private AvionDto avionesId;
    private PistaDto pistasId;
    private LugarDto lugarSalida;
    private LugarDto lugarLlegada;
    private AlertaDto alerta;
}
