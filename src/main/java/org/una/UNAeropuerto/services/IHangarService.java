package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.HangarDto;
import java.util.List;

public interface IHangarService {

    public HangarDto getById(long id);

    public List<HangarDto> findByEstado(boolean estado);

    public List<HangarDto> findByEspecialidadAproximado(String parametro);

    public HangarDto update(HangarDto cobro);

    public HangarDto create(HangarDto cobro);
}
