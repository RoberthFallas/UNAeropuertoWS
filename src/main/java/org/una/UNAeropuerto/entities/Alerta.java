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
    @Size(max = 40)
    @Column(name = "emisor")
    private String emisor;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "activa")
    private Boolean activa;
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Area areaId;

}
