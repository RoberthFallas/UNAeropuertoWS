/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.dto;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Roberth :)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BitacoraDto {
    
    private Long id;
    private Date fechaModificacion;
    private String tipoCambio;
    private UsuarioDto usuariosId;
    
}
