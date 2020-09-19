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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "gastos_reparaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GastoReparacion.findAll", query = "SELECT g FROM GastoReparacion g")})
public class GastoReparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_pago")
    private short estadoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_contrato")
    private long numeroContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private int duracion;
    @Column(name = "periodicidad")
    private Integer periodicidad;
    @Size(max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gastosReparacionesId")
    private List<DetalleServicio> detalleServicioList;
    @JoinColumn(name = "areas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Area areasId;

    public GastoReparacion() {
    }

    public GastoReparacion(Long id) {
        this.id = id;
    }

    public GastoReparacion(Long id, Date fechaRegistro, short estadoPago, long numeroContrato, int duracion, short activo) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.estadoPago = estadoPago;
        this.numeroContrato = numeroContrato;
        this.duracion = duracion;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public short getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(short estadoPago) {
        this.estadoPago = estadoPago;
    }

    public long getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(long numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Integer getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Integer periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<DetalleServicio> getDetalleServicioList() {
        return detalleServicioList;
    }

    public void setDetalleServicioList(List<DetalleServicio> detalleServicioList) {
        this.detalleServicioList = detalleServicioList;
    }

    public Area getAreasId() {
        return areasId;
    }

    public void setAreasId(Area areasId) {
        this.areasId = areasId;
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
        if (!(object instanceof GastoReparacion)) {
            return false;
        }
        GastoReparacion other = (GastoReparacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.una.UNAeropuerto.entities.GastoReparacion[ id=" + id + " ]";
    }
    
}
