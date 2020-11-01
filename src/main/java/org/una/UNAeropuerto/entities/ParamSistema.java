/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Roberth :)
 */
@Entity
@Table(name = "param_sistema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParamSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "vuelos_hora")
    private Short vuelosHora;
    @Basic(optional = false)
    @Column(name = "tiempo_inactividad")
    private Integer tiempoInactividad;
    @Basic(optional = false)
    @Size(min = 1, max = 25)
    @Column(name = "telefono_aeropuerto")
    private String telefonoAeropuerto;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "email_aeropuerto")
    private String emailAeropuerto;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "nombre_representante")
    private String nombreRepresentante;
    @Basic(optional = false)
    @Column(name = "apertura_oficina")
    @Temporal(TemporalType.TIME)
    private Date aperturaOficina;
    @Basic(optional = false)
    @Column(name = "cierre_oficina")
    @Temporal(TemporalType.TIME)
    private Date cierreOficina;
    @OneToOne
    @JoinColumn(name = "ubicacion_aeropuerto", referencedColumnName = "id")
    private Lugar ubicacion;

}
