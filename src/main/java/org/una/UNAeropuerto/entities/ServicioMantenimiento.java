/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Roberth :)
 */
@Entity
@Table(name = "servicios_mantenimientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServicioMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "fecha_servicio")
    @Temporal(TemporalType.DATE)
    private Date fechaServicio;
    @Basic(optional = false)
    @Column(name = "numero_factura")
    private Long numeroFactura;
    @Basic(optional = false)
    @Column(name = "estado_pago")
    private Boolean estadoPago;
    @Basic(optional = false)
    @Column(name = "esta_finalizacion")
    private Boolean estaFinalizacion;
    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;
    @JoinColumn(name = "aviones_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Avion avionesId;
    @JoinColumn(name = "tipos_servicios_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private TipoServicio tiposServiciosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviciosMantenimientoId")
    private List<Cobro> cobroList;

   

}
