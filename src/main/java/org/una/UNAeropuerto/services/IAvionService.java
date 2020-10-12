package org.una.UNAeropuerto.services;

import org.una.UNAeropuerto.dto.AvionDto;
import java.util.List;

public interface IAvionService {

    public AvionDto getById(long id);

    public AvionDto getByMatricula(String matricula);

    public List<AvionDto> findByEstado(boolean estado);

    public List<AvionDto> findByAerolineaId(long id);

    public AvionDto update(AvionDto avion);

    public AvionDto create(AvionDto avion);
}
