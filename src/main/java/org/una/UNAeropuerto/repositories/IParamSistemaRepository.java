package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.UNAeropuerto.entities.ParamSistema;

public interface IParamSistemaRepository extends JpaRepository<ParamSistema, Integer> {

    @Query("select p.tiempoInactividad from ParamSistema p where p.id = 1")
    public Integer getSesionDurationMinutos();
}
