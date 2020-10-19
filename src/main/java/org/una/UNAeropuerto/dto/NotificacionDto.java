package org.una.UNAeropuerto.dto;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificacionDto {
    int id;
    boolean activo;
    int idObjeto;
    Date fechaRegistro;
    @Setter(AccessLevel.NONE)
    AreaDto areasId;
}