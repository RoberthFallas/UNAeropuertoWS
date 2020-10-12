/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.List;
import org.una.UNAeropuerto.dto.AlertaDto;

/**
 *
 * @author Roberth :)
 */
public interface IAlertaService {

    public AlertaDto getById(long id);

    public List<AlertaDto> findByTipo(byte tipo);

    public List<AlertaDto> findByTitulo(String titulo);

    public List<AlertaDto> findByEmisor(String titulo);



    public AlertaDto create(AlertaDto alerta);

    public AlertaDto update(AlertaDto alerta);

}
