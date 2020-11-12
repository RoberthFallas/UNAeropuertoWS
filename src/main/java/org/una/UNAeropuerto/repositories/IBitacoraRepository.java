/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.UNAeropuerto.entities.Bitacora;

/**
 *
 * @author Roberth :)
 */
public interface IBitacoraRepository extends JpaRepository<Bitacora, Long> {

    public Optional<List<Bitacora>> findByTipoCambioContaining(String tipoCambio);

    public Optional<List<Bitacora>> findByusuariosIdId(long id);

    public Optional<List<Bitacora>> findByUsuariosIdCedula(String nombre);

   // @Query("select b from Bitacora b where DATE(b.fechaModificacion)like :fechaModificacion")
    public Optional<List<Bitacora>> findByFechaModificacion(@Param("fechaModificacion") Date fechaModificacion);

    public Optional<List<Bitacora>> findByFechaModificacionBetween( Date fechaInicio,  Date fechaFinal);

    @Query("select b from Bitacora b join b.usuariosId u  where"
            + " UPPER(b.tipoCambio) like CONCAT('%',UPPER(:accion),'%')"
            + " or UPPER(u.nombre )like CONCAT('%',UPPER(:nombre),'%')"
            + " and UPPER(u.apellidos) like CONCAT('%',UPPER(:apellido),'%')"
            + " and UPPER(u.cedula ) like CONCAT('%',UPPER(:cedula),'%')"
            + " and b.fechaModificacion between :date1 and :date2")
    public  Optional<List<Bitacora>> busquedaMixta(@Param("accion") String accion, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("cedula") String cedula, @Param("date1") Date date1, @Param("date2") Date date2 );

}
