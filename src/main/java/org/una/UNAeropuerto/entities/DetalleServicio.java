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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "detalles_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleServicio.findAll", query = "SELECT d FROM DetalleServicio d")})
public class DetalleServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @JoinColumn(name = "tipos_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipo tiposId;
    @JoinColumn(name = "provedores_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Provedor provedoresId;
    @JoinColumn(name = "gastos_reparaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GastoReparacion gastosReparacionesId;

    public DetalleServicio() {
    }

    public DetalleServicio(Long id) {
        this.id = id;
    }

    public DetalleServicio(Long id, float monto, short activo) {
        this.id = id;
        this.monto = monto;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public Tipo getTiposId() {
        return tiposId;
    }

    public void setTiposId(Tipo tiposId) {
        this.tiposId = tiposId;
    }

    public Provedor getProvedoresId() {
        return provedoresId;
    }

    public void setProvedoresId(Provedor provedoresId) {
        this.provedoresId = provedoresId;
    }

    public GastoReparacion getGastosReparacionesId() {
        return gastosReparacionesId;
    }

    public void setGastosReparacionesId(GastoReparacion gastosReparacionesId) {
        this.gastosReparacionesId = gastosReparacionesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleServicio)) {
            return false;
        }
        DetalleServicio other = (DetalleServicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.una.UNAeropuerto.entities.DetalleServicio[ id=" + id + " ]";
    }
    
}
