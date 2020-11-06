/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.UNAeropuerto.entities.Usuario;

/**
 *
 * @author Roberth :)
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByCedula(String cedula);

    public Optional<List<Usuario>> findByCedulaContaining(String parameter);

    @Query("select u from Usuario u where UPPER(u.nombre) like CONCAT('%',UPPER(:nombre),'%') or UPPER(u.apellidos) like CONCAT('%',UPPER(:apellidos),'%')")
    public Optional<List<Usuario>> findByNombreAndApellido(@Param("nombre") String nombre, @Param("apellidos") String apellidos);

    @Query("select u from Usuario u where UPPER(u.nombre) like CONCAT('%',UPPER(:nombre),'%') and UPPER(u.apellidos) like CONCAT('%',UPPER(:apellidos),'%') and UPPER(u.cedula) like CONCAT('%',UPPER(:cedula),'%')and u.activo=:activo")
    public Optional<List<Usuario>> busquedaMixta(@Param("nombre") String nombre, @Param("apellidos") String apellidos, @Param("cedula") String cedula, @Param("activo")boolean activo);

    @Query("select u from Usuario u where UPPER(u.nombre) like CONCAT('%',UPPER(:nombre),'%') and UPPER(u.apellidos) like CONCAT('%',UPPER(:apellidos),'%') and UPPER(u.cedula) like CONCAT('%',UPPER(:cedula),'%')and  u.activo=:activo and :fechaIngreso BETWEEN :fechaInicio and :fechaFinal")
    public Optional<List<Usuario>> busquedaMixtaConFecha(@Param("nombre") String nombre, @Param("apellidos") String apellidos, @Param("cedula") String cedula,@Param("activo") boolean activo,@Param("fechaInicio")Date fechaInicio,@Param("fechaFinal")Date fechaFinal);
}
