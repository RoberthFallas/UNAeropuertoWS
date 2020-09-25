package org.una.UNAeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.UNAeropuerto.entities.Cobro;


import java.util.List;
import java.util.Optional;

public interface ICobroRepository extends JpaRepository<Cobro, Long> {

    public Optional<List<Cobro>> findByActivoLike(boolean estado);

    public Optional<List<Cobro>> findByServiciosMantenimientoId(long id);

    public Optional<List<Cobro>> findByMontoContaining(long monto);

    public Optional<List<Cobro>> findByMontoBetween(long montoInicial, long montoFinal);

    public Optional<List<Cobro>> findByDetalleCobroContaining(String  parametro);












    
    
}
