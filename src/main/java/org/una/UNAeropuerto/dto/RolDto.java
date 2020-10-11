/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RolDto {

    private Long id;
    private String nombre;
    private Boolean activo;
    @Setter(AccessLevel.NONE)
    private List<RolUsuarioDto> rolUsuarioList;

    public Long getId() {
        return id;
    }
}
