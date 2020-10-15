/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ParamSistemaDto {

    private Long id;
    private Short vuelosHora;
    private Integer tiempoInactividad;
    private String telefonoAeropuerto;
    private String emailAeropuerto;
    private String nombreRepresentante;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date aperturaOficina;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cierreOficina;
}
