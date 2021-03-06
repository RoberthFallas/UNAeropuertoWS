package org.una.UNAeropuerto.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;
    @Basic(optional = false)
    @Column(name = "idObjeto")
    private Long idObjeto;
    @Basic(optional = false)
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "areas_id", referencedColumnName = "id")
    @ManyToOne(/*optional = false*/)
    private Area areasId;

    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
    }

}
