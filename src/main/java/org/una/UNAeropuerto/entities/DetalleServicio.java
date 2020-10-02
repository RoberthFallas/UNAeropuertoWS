/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Roberth :)
 */
@Entity
@Table(name = "detalles_servicios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "monto")
    private Float monto;
    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;
    @JoinColumn(name = "tipos_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Tipo tiposId;
    @JoinColumn(name = "provedores_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Provedor provedoresId;
    @JoinColumn(name = "gastos_reparaciones_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private GastoReparacion gastosReparacionesId;

}
