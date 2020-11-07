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
import javax.persistence.ManyToOne;
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
@Table(name = "vuelos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Size(min = 1, max = 40)
    @Column(name = "nombre_vuelo")
    private String nombreVuelo;
    @Basic(optional = false)
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSalida;
    @Basic(optional = false)
    @Column(name = "hora_llegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaLlegada;
    @Basic(optional = false)
    @Column(name = "estado")
    private Byte estado;
    @JoinColumn(name = "aviones_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Avion avionesId;
    @JoinColumn(name = "pistas_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Pista pistasId;
    @JoinColumn(name = "lugar_salida_id", referencedColumnName = "id")
    @ManyToOne()
    private Lugar lugarSalida;
    @JoinColumn(name = "lugar_llegada_id", referencedColumnName = "id")
    @ManyToOne()
    private Lugar lugarLlegada;
    @JoinColumn(name = "alertas_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Alerta alertasId;
    @JoinColumn(name = "tipo_vuelo_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private TipoVuelo tipoVuelo;

}
