package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.HangarDto;
import org.una.UNAeropuerto.dto.HangarDto;
import org.una.UNAeropuerto.entities.Hangar;

import java.util.List;
import java.util.Optional;

public interface IHangarService {

    public HangarDto getById(long id);

    public List<HangarDto> findByEstado(boolean estado);

    public List<HangarDto> findByEspecialidadAproximado(String parametro);

    public HangarDto update(HangarDto cobro);

    public HangarDto create(HangarDto cobro);
}
