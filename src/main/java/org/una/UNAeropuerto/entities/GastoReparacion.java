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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "gastos_reparaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GastoReparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "estado_pago")
    private Boolean estadoPago;
    @Basic(optional = false)
    @Column(name = "numero_contrato")
    private Long numeroContrato;
    @Basic(optional = false)
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "periodicidad")
    private Integer periodicidad;
    @Size(max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;
    @JoinColumn(name = "areas_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Area areasId;
    @JoinColumn(name = "tipos_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private TipoReparacion tiposId;
    @JoinColumn(name = "provedores_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Provedor provedoresId;

    @PrePersist
    public void prePersist() {

        fechaRegistro = new Date();

    }

    @PreUpdate
    public void preUpdate() {
       fechaRegistro = new Date();
    }

}
