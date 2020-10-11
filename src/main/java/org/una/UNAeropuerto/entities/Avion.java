/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
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
@Table(name = "aviones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Avion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avionesId")
    private List<ServicioMantenimiento> servicioMantenimientoList;
    @JoinColumn(name = "aerolineas_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Aerolinea aerolineasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avionesId")
    private List<Vuelo> vueloList;

}
