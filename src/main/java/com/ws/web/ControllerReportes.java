package com.ws.web;

import com.ws.entidades.dto.ProductoDto;
import com.ws.servicios.impl.AlmacenServiceImpl;
import com.ws.servicios.impl.OrdenDeCompraServiceImpl;

import io.reactivex.Maybe;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ControllerReportes {



    @Autowired
    AlmacenServiceImpl almacenService;
    
    
    @Autowired
    OrdenDeCompraServiceImpl ordenCompraService;

    @GetMapping(value = "/generarReporte")
    public Maybe<String> generateReport( HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {


     	String id = request.getParameter("id");
           

        return ordenCompraService.buscarPorId(id).map(ordenCompra -> {
        	
        	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ordenCompra.getProductos(),false);
            Map<String, Object> parameters = new HashMap<String, Object>();
            
            parameters.put("razon-social", ordenCompra.getRazonSocial());

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
        });
    }



    




}
