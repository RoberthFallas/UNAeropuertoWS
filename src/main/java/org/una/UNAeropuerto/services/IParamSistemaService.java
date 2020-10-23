package org.una.UNAeropuerto.services;

import java.util.Optional;
import org.una.UNAeropuerto.dto.ParamSistemaDto;

public interface IParamSistemaService {

    public Optional<ParamSistemaDto> getById(Integer id);

    public ParamSistemaDto create(ParamSistemaDto paramSistema);

    public ParamSistemaDto update(ParamSistemaDto paramSistema);

}
