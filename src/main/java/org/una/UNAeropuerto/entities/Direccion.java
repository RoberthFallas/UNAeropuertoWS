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
@Table(name = "direcciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d")})
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "direccion_vuelo")
    private String direccionVuelo;
    @JoinColumn(name = "vuelos_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vuelo vuelosId;
    @JoinColumn(name = "lugares_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lugar lugaresId;

    public Direccion() {
    }

    public Direccion(Long id) {
        this.id = id;
    }

    public Direccion(Long id, String direccionVuelo) {
        this.id = id;
        this.direccionVuelo = direccionVuelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccionVuelo() {
        return direccionVuelo;
    }

    public void setDireccionVuelo(String direccionVuelo) {
        this.direccionVuelo = direccionVuelo;
    }

    public Vuelo getVuelosId() {
        return vuelosId;
    }

    public void setVuelosId(Vuelo vuelosId) {
        this.vuelosId = vuelosId;
    }

    public Lugar getLugaresId() {
        return lugaresId;
    }

    public void setLugaresId(Lugar lugaresId) {
        this.lugaresId = lugaresId;
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
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.una.UNAeropuerto.entities.Direccion[ id=" + id + " ]";
    }
    
}
