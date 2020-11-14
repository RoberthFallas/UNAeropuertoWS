/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.util.Date;
import java.util.Optional;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author LordLalo
 */
public interface IReportService {

    public Optional<JasperPrint> reporteServicios(Date fechaInicial, Date fechaFinal, String nombre);

    public Optional<JasperPrint> reporteMantenimiento(Date fechaInicial, Date fechaFinal, String nombre);

    public Optional<JasperPrint> reporteVuelo(Date fechaInicial, Date fechaFinal, String area,String tipoVuelo);
}
