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

import java.util.List;

/**
 *
 * @author Roberth :)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlertaDto {

    private Long id;
    private Byte tipo;
    private String titulo;
    private String cuerpo;
    private Boolean activa;
    @Setter(AccessLevel.NONE)
    private List<VueloDto> vueloList;
}
