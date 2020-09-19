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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "servicios_mantenimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioMantenimiento.findAll", query = "SELECT s FROM ServicioMantenimiento s")})
public class ServicioMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_servicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_factura")
    private long numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_pago")
    private short estadoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_finalizacion")
    private short estaFinalizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @JoinColumn(name = "aviones_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Avion avionesId;
    @JoinColumn(name = "hangares_id", referencedColumnName = "id")
    @ManyToOne
    private Hangar hangaresId;
    @JoinColumn(name = "tipos_servicios_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoServicio tiposServiciosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviciosMantenimientoId")
    private List<Cobro> cobroList;

    public ServicioMantenimiento() {
    }

    public ServicioMantenimiento(Long id) {
        this.id = id;
    }

    public ServicioMantenimiento(Long id, Date fechaServicio, long numeroFactura, short estadoPago, short estaFinalizacion, short activo) {
        this.id = id;
        this.fechaServicio = fechaServicio;
        this.numeroFactura = numeroFactura;
        this.estadoPago = estadoPago;
        this.estaFinalizacion = estaFinalizacion;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public short getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(short estadoPago) {
        this.estadoPago = estadoPago;
    }

    public short getEstaFinalizacion() {
        return estaFinalizacion;
    }

    public void setEstaFinalizacion(short estaFinalizacion) {
        this.estaFinalizacion = estaFinalizacion;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public Avion getAvionesId() {
        return avionesId;
    }

    public void setAvionesId(Avion avionesId) {
        this.avionesId = avionesId;
    }

    public Hangar getHangaresId() {
        return hangaresId;
    }

    public void setHangaresId(Hangar hangaresId) {
        this.hangaresId = hangaresId;
    }

    public TipoServicio getTiposServiciosId() {
        return tiposServiciosId;
    }

    public void setTiposServiciosId(TipoServicio tiposServiciosId) {
        this.tiposServiciosId = tiposServiciosId;
    }

    @XmlTransient
    public List<Cobro> getCobroList() {
        return cobroList;
    }

    public void setCobroList(List<Cobro> cobroList) {
        this.cobroList = cobroList;
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
        if (!(object instanceof ServicioMantenimiento)) {
            return false;
        }
        ServicioMantenimiento other = (ServicioMantenimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.una.UNAeropuerto.entities.ServicioMantenimiento[ id=" + id + " ]";
    }
    
}
