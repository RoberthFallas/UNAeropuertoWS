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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author roberth :)
 */
@Entity
@Table(name = "tipos_vuelos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoVuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(length = 30)
    private String nombre;
    @Basic(optional = false)
    @Column
    private Boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoVuelo")
    private List<Vuelo> vueloList;
    
}
