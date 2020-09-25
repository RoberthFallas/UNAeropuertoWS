package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.ParamSistemaDto;

public interface IParamSistemaService {


    public ParamSistemaDto getById(long id);

    public ParamSistemaDto create(ParamSistemaDto paramSistema);

    public ParamSistemaDto update(ParamSistemaDto paramSistema);

}
