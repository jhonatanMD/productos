package com.ws.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "OrdenDeSalida")
public class OrdenDeSalida {

    @Id
    private String id;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String areaResponsable;
    private String nombreCompleto;
    private String dni;
    private String cliente;
    private String unidad;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fecha;
    private String motivo;
    private String numeroSerie;
    private List<OrdenDeSalida.ProductoOrdenSalida> productos;
    private String observaciones;
    private Long sede;
    private String estado;

    @Getter
    @Setter
    public static class ProductoOrdenSalida{

        private String id;
        private String articulo;
        private Long cantidad;
        private OrdenDeSalida.Estado estadoArticulo;
    }

    @Getter
    @Setter
    public static class Estado{
        private String nuevo;
        private String usado;
    }
}
