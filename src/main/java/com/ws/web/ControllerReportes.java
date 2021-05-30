package com.ws.web;

import com.ws.servicios.impl.AlmacenServiceImpl;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ControllerReportes {



    @Autowired
    AlmacenServiceImpl almacenService;

    @GetMapping(value = "/generarReporte")
    public String generateReport( HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {


        List<Reporte> personas = List.of(new Reporte(1,"jhonatan","mallqui"),
                new Reporte(2,"jhonatan","mallqui"),new Reporte(3,"jhonatan","mallqui"),
                new Reporte(4,"jhonatan","mallqui"));

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(personas,false);
        Map<String, Object> parameters = new HashMap<String, Object>();

        final InputStream stream = this.getClass().getResourceAsStream("/almacen.jrxml");
        JasperReport archivo = JasperCompileManager.compileReport(stream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(archivo,parameters,beanColDataSource);

        JRPdfExporter exporter = new JRPdfExporter();
        SimplePdfReportConfiguration reportConfigPDF = new SimplePdfReportConfiguration();
        exporter.setConfiguration(reportConfigPDF);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader("Content-Disposition", "attachment;filename=almacen.pdf");
        response.setContentType("application/octet-stream");
        exporter.exportReport();

        return "LISTO";
    }





    @Getter
    @Setter
    public class Reporte{
        private Integer id;
        private String nombre;
        private String apellido;

        public Reporte(Integer id, String nombre, String apellido) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
        }
    }




}