/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.controllers;

import io.swagger.annotations.Api;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.UNAeropuerto.services.IReportService;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/reporte")
@Api(tags = {"Reportes"})
public class ReporteController {

    @Autowired
    private IReportService reporte;

    @GetMapping("/generateReportService/{fechaInicial}/{fechaFinal}/{nombre}")
    @PreAuthorize("hasAuthority('AUDITOR_SERVICIOS_AERONAVES')")
    public ResponseEntity<?> generateReportService(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
            @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal,
            @PathVariable(value = "nombre") String nombre) throws IOException, ParseException {
        byte[] jasperReportByte;
        String pNombre = nombre.replace('_', ' ');
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        JasperPrint jasperPrint = reporte.reporteServicios(fechaInicial, fechaFinal, pNombre).get();
        outputStream.writeObject(jasperPrint);
        jasperReportByte = baos.toByteArray();
        String enviar = Base64.getEncoder().encodeToString(jasperReportByte);
        return new ResponseEntity<>(enviar, HttpStatus.OK);
    }

    @GetMapping("/generateReportMantenimiento/{fechaInicial}/{fechaFinal}/{nombre}")
    @PreAuthorize("hasAuthority('AUDITOR_MANTENIMIENTO_AEROPUERTO')")
    public ResponseEntity<?> generateReportMantenimiento(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
            @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal,
            @PathVariable(value = "nombre") String nombre)
            throws IOException, ParseException {
        byte[] jasperReportByte;
        String nom = nombre.replace('_', ' ');
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        JasperPrint jasperPrint = reporte.reporteMantenimiento(fechaInicial, fechaFinal, nom).get();
        outputStream.writeObject(jasperPrint);
        jasperReportByte = baos.toByteArray();
        String enviar = Base64.getEncoder().encodeToString(jasperReportByte);
        return new ResponseEntity<>(enviar, HttpStatus.OK);
    }

    @GetMapping("/generateReportVuelo/{fechaInicial}/{fechaFinal}/{area}/{tipoVuelo}")
    @PreAuthorize("hasAuthority('AUDITOR_CONTROL_VUELOS')")
    public ResponseEntity<?> generateReportVuelo(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
            @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal,
            @PathVariable(value = "area") String area, @PathVariable(value = "tipoVuelo") String tipoVuelo)
            throws IOException, ParseException {
        byte[] jasperReportByte;
        String pArea = area.replace('_', ' ');
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        JasperPrint jasperPrint = reporte.reporteVuelo(fechaInicial, fechaFinal, pArea, tipoVuelo).get();
        outputStream.writeObject(jasperPrint);
        jasperReportByte = baos.toByteArray();
        String enviar = Base64.getEncoder().encodeToString(jasperReportByte);
        return new ResponseEntity<>(enviar, HttpStatus.OK);
    }
}
