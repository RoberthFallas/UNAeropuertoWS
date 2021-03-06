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
public class RolUsuarioDto {

    private Long id;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private Boolean activo;
    //  @Setter(AccessLevel.NONE)
    private RolDto rolesId;
    @Setter(AccessLevel.NONE)
    private UsuarioDto usuariosId;
}
