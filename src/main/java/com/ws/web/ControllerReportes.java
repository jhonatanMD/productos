package com.ws.web;

import com.ws.entidades.dto.ProductoOrdenSalidad;
import com.ws.servicios.impl.AlmacenServiceImpl;
import com.ws.servicios.impl.OrdenDeCompraServiceImpl;
import com.ws.servicios.impl.OrdenDeSalidaServiceImpl;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ControllerReportes {



    @Autowired
    AlmacenServiceImpl almacenService;
    
    
    @Autowired
    OrdenDeCompraServiceImpl ordenCompraService;

    @Autowired
    OrdenDeSalidaServiceImpl ordenDeSalidaService;

    @GetMapping(value = "/generarReporteOrdenCompra")
    public Maybe<String> generarReporteOrdenCompra( HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {


     	String id = request.getParameter("id");
           

        return ordenCompraService.buscarPorId(id).map(ordenCompra -> {
        	
        	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(ordenCompra.getProductos(),false);
            Map<String, Object> parameters = new HashMap<String, Object>();
            
            parameters.put("razon-social", ordenCompra.getRazonSocial());
            parameters.put("ruc-empresa",ordenCompra.getRucEmpresa());
            parameters.put("direccion-empresa",ordenCompra.getDireccionEmpresa());
            parameters.put("numero-serie",ordenCompra.getNumeroSerie());
            parameters.put("sres-empresa",ordenCompra.getSresEmpresa());
            parameters.put("ruc-proveedor",ordenCompra.getRucProveedor());
            parameters.put("direccion-proveedor",ordenCompra.getDireccionProveedor());
            parameters.put("fecha",ordenCompra.getFecha());
            parameters.put("email-proveedor",ordenCompra.getEmailProveedor());
            parameters.put("telefono1-proveedor",ordenCompra.getTelefono1Proveedor());
            parameters.put("telefono2-proveedor",ordenCompra.getTelefono2Proveedor());
            parameters.put("atencion-proveedor",ordenCompra.getAtencionProveedor());
            parameters.put("cotizacion-proveedor",ordenCompra.getCotizacionProveedor());
            parameters.put("razon-social", ordenCompra.getRazonSocial());
            parameters.put("tipo-cuenta", ordenCompra.getTipoCuenta());
            parameters.put("requerimiento-proveedor",ordenCompra.getRequerimientosProveedor());
            parameters.put("unidad-proveedor",ordenCompra.getUnidadProveedor());
            parameters.put("motivo-proveedor",ordenCompra.getMotivoProveedor());
            parameters.put("formato-pago",ordenCompra.getFormatoPago());
            parameters.put("plazo-entrega",ordenCompra.getPlazoEntrega());
            parameters.put("numero-cuenta",ordenCompra.getNumeroCuenta());
            parameters.put("subTotal",ordenCompra.getSubTotal());
            parameters.put("igv",ordenCompra.getIgv());
            parameters.put("totalPagar",ordenCompra.getTotalPagar());
            parameters.put("total_texto",ordenCompra.getTotalTexto());
            parameters.put("observacion",ordenCompra.getObservacion());

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



    @GetMapping(value = "/generarReporteOrdenSalida")
    public Maybe<String> generarReporteOrdenSalida( HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {


        String id = request.getParameter("id");
      return  ordenDeSalidaService.buscarOrdenPorId(id).map(orden -> {

          List<ProductoOrdenSalidad> productos = new ArrayList<>();
          Map<String, Object> parameters = new HashMap<String, Object>();


          parameters.put("razon-social",orden.getRazonSocial());
          parameters.put("ruc-empresa",orden.getRuc());
          parameters.put("direccion-empresa",orden.getDireccion());
          parameters.put("numero-serie",orden.getNumeroSerie());
          parameters.put("area-responsable",orden.getAreaResponsable());
          parameters.put("apellido-nombre",orden.getNombreCompleto());
          parameters.put("dni",orden.getDni());
          parameters.put("cliente",orden.getCliente());
          parameters.put("unidad",orden.getUnidad());
          parameters.put("motivo",orden.getMotivo());
          parameters.put("fecha",orden.getFecha().toString());
          parameters.put("observacion",orden.getObservaciones());




          JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(orden.getProductos().parallelStream().map(prod ->
                  new ProductoOrdenSalidad(prod.getArticulo(), prod.getCantidad().toString(), prod.getEstadoArticulo().getNuevo(),
                          prod.getEstadoArticulo().getUsado())).collect(Collectors.toList()),false);
          final InputStream stream = this.getClass().getResourceAsStream("/ordenSalida.jrxml");
          JasperReport archivo = JasperCompileManager.compileReport(stream);
          JasperPrint jasperPrint = JasperFillManager.fillReport(archivo,parameters,beanColDataSource);

          JRPdfExporter exporter = new JRPdfExporter();
          SimplePdfReportConfiguration reportConfigPDF = new SimplePdfReportConfiguration();
          exporter.setConfiguration(reportConfigPDF);
          exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
          exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
          response.setHeader("Content-Disposition", "attachment;filename=orden-salida.pdf");
          response.setContentType("application/octet-stream");
          exporter.exportReport();
            return "LISTO";
        });



    }





}
