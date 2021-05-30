package com.ws.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class ProductoConsulta {

    private String producto;
    private String marca;
    private List<String> tipos = new ArrayList<>();
    private String color;
    private String talla;
    private String genero;
    private BigDecimal precio;
    private String descripcion;
    private long stock;
    private long minimo_stock;



    public List<String> addTipos(String tipo){
        tipos.add(tipo);
        return tipos;
    }



    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class RespuestaConsultaProducto{
        private String msj;
        private List<ProductoConsulta> consulta;
    }
}
