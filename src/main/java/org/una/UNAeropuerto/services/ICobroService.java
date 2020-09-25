package org.una.UNAeropuerto.services;
import org.una.UNAeropuerto.dto.CobroDto;
import org.una.UNAeropuerto.dto.CobroDto;
import java.util.List;


public interface ICobroService {

    public CobroDto getById(long id);

    public List<CobroDto> findByActivos(boolean estado);

    public List<CobroDto> findByServiciosMantenimientoId(long id);

    public List<CobroDto> findByMontoAproximado(long monto);

    public List<CobroDto> findByMontoBetween(long montoInicial, long montoFinal);

    public List<CobroDto> findByDetalleCobroAproximado(String  parametro);

    public CobroDto update(CobroDto cobro);

    public CobroDto create(CobroDto cobro);

}
