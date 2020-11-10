/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.services;

import java.io.File;
import java.net.URL;
import static java.net.URLDecoder.decode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.una.UNAeropuerto.UnAeropuertoApplication;

/**
 *
 * @author LordLalo
 */
@Service
public class ReportServiceImplementation implements IReportService {

    @Override
    public Optional<JasperPrint> reporteServicios(Date fechaInicial, Date fechaFinal, String nombre) {
        try {
            final Map parameters = new HashMap();
            parameters.put("fechaInicial", modificarFecha(fechaInicial));
            parameters.put("fechaFinal", modificarFecha(fechaFinal));
            parameters.put("nombre", nombre);
            System.err.println(UnAeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation());
            URL url = UnAeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation();
            String rutaUrl = decode(url.toURI().toString());
            System.out.println(rutaUrl);
            String ruta = rutaUrl.replaceFirst("file:/", "");
            String report = ruta.replaceAll("/target/classes/", "/src/main/java/org/una/UNAeropuerto/report/"
                    + "ServicioReport" + ".jasper");
            String ruta1 = report.replaceAll("/", "\\\\");
            String rutaFinal = ruta1.replaceAll("%20", " ");
            System.out.println("[" + rutaFinal + "]");
            File file = new File(rutaFinal);
            System.out.println(file.toString());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, crearConnection());
            return Optional.of(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Connection crearConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://107.180.0.205:3306/BD_PF_Grupo3", "BD_PF_Grupo3_U", "c)6UeR3!jREC7X8");
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    private Date modificarFecha(Date form) throws ParseException {
        int mes = form.getMonth() + 1;
        int anno = form.getYear() + 1900;
        int dia = form.getDate();
        String fecha = anno + "-" + mes + "-" + dia;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(fecha);
    }

    @Override
    public Optional<JasperPrint> reporteMantenimiento(Date fechaInicial, Date fechaFinal, String nombre) {
        try {
            final Map parameters = new HashMap();
            parameters.put("fechaInicio", modificarFecha(fechaInicial));
            parameters.put("fechaFinal", modificarFecha(fechaFinal));
            parameters.put("nombre", nombre);
            System.err.println(UnAeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation());
            URL url = UnAeropuertoApplication.class.getProtectionDomain().getCodeSource().getLocation();
            String rutaUrl = decode(url.toURI().toString());
            System.out.println(rutaUrl);
            String ruta = rutaUrl.replaceFirst("file:/", "");
            String report = ruta.replaceAll("/target/classes/", "/src/main/java/org/una/UNAeropuerto/report/"
                    + "MantenimientoReport" + ".jasper");
            String ruta1 = report.replaceAll("/", "\\\\");
            String rutaFinal = ruta1.replaceAll("%20", " ");
            System.out.println("[" + rutaFinal + "]");
            File file = new File(rutaFinal);
            System.out.println(file.toString());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, crearConnection());
            return Optional.of(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
