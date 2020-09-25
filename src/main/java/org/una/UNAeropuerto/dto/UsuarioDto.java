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
public class UsuarioDto {

    private Long id;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String contrasenna;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private Date fechaModificacion;
    private Boolean activo;
    @Setter(AccessLevel.NONE)
    private List<BitacoraDto> bitacoraList;
   // @Setter(AccessLevel.NONE)
    private List<RolUsuarioDto> rolUsuarioList;
    @Setter(AccessLevel.NONE)
    private AreaDto areasId;
}
