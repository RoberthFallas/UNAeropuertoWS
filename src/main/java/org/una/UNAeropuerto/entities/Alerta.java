/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
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
@Table(name = "alertas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Alerta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private Byte tipo;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "cuerpo")
    private String cuerpo;
    
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "activa")
    private Boolean activa;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "alertasId")
    private List<Vuelo> vuelosList;

}
