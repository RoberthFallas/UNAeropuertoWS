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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "param_sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParamSistema.findAll", query = "SELECT p FROM ParamSistema p")})
public class ParamSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vuelos_hora")
    private int vuelosHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_inactividad")
    private int tiempoInactividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "telefono_aeropuerto")
    private String telefonoAeropuerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email_aeropuerto")
    private String emailAeropuerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_representante")
    private String nombreRepresentante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apertura_oficina")
    @Temporal(TemporalType.TIME)
    private Date aperturaOficina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cierre_oficina")
    @Temporal(TemporalType.TIME)
    private Date cierreOficina;

    public ParamSistema() {
    }

    public ParamSistema(Integer id) {
        this.id = id;
    }

    public ParamSistema(Integer id, int vuelosHora, int tiempoInactividad, String telefonoAeropuerto, String emailAeropuerto, String nombreRepresentante, Date aperturaOficina, Date cierreOficina) {
        this.id = id;
        this.vuelosHora = vuelosHora;
        this.tiempoInactividad = tiempoInactividad;
        this.telefonoAeropuerto = telefonoAeropuerto;
        this.emailAeropuerto = emailAeropuerto;
        this.nombreRepresentante = nombreRepresentante;
        this.aperturaOficina = aperturaOficina;
        this.cierreOficina = cierreOficina;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVuelosHora() {
        return vuelosHora;
    }

    public void setVuelosHora(int vuelosHora) {
        this.vuelosHora = vuelosHora;
    }

    public int getTiempoInactividad() {
        return tiempoInactividad;
    }

    public void setTiempoInactividad(int tiempoInactividad) {
        this.tiempoInactividad = tiempoInactividad;
    }

    public String getTelefonoAeropuerto() {
        return telefonoAeropuerto;
    }

    public void setTelefonoAeropuerto(String telefonoAeropuerto) {
        this.telefonoAeropuerto = telefonoAeropuerto;
    }

    public String getEmailAeropuerto() {
        return emailAeropuerto;
    }

    public void setEmailAeropuerto(String emailAeropuerto) {
        this.emailAeropuerto = emailAeropuerto;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public Date getAperturaOficina() {
        return aperturaOficina;
    }

    public void setAperturaOficina(Date aperturaOficina) {
        this.aperturaOficina = aperturaOficina;
    }

    public Date getCierreOficina() {
        return cierreOficina;
    }

    public void setCierreOficina(Date cierreOficina) {
        this.cierreOficina = cierreOficina;
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
        if (!(object instanceof ParamSistema)) {
            return false;
        }
        ParamSistema other = (ParamSistema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.una.UNAeropuerto.entities.ParamSistema[ id=" + id + " ]";
    }
    
}
