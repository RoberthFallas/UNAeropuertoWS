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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "cobros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cobro.findAll", query = "SELECT c FROM Cobro c")})
public class Cobro implements Serializable {

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
    @Size(min = 1, max = 50)
    @Column(name = "detalle_cobro")
    private String detalleCobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @JoinColumn(name = "servicios_mantenimiento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ServicioMantenimiento serviciosMantenimientoId;

    public Cobro() {
    }

    public Cobro(Long id) {
        this.id = id;
    }

    public Cobro(Long id, float monto, String detalleCobro, short activo) {
        this.id = id;
        this.monto = monto;
        this.detalleCobro = detalleCobro;
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

    public String getDetalleCobro() {
        return detalleCobro;
    }

    public void setDetalleCobro(String detalleCobro) {
        this.detalleCobro = detalleCobro;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public ServicioMantenimiento getServiciosMantenimientoId() {
        return serviciosMantenimientoId;
    }

    public void setServiciosMantenimientoId(ServicioMantenimiento serviciosMantenimientoId) {
        this.serviciosMantenimientoId = serviciosMantenimientoId;
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
        if (!(object instanceof Cobro)) {
            return false;
        }
        Cobro other = (Cobro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.una.UNAeropuerto.entities.Cobro[ id=" + id + " ]";
    }
    
}
