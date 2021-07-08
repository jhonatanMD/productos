package com.ws.entidades.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class AlmacenProductoConsulta  {


    private String idAlmacen;
    private AlmacenProductoConsulta.Producto producto;
    private AlmacenProductoConsulta.Marca marca;
    private List<Tipo> tipos = new ArrayList<>();
    private String displayName;
    private String color;
    private String talla;
    private String genero;
    private BigDecimal precio;
    private String descripcion;
    private long stock;
    private long minimo_stock;


    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fecha_ingreso;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fecha_actualizacion;



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Marca{

        private long id;
        private String marca;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Producto{

        private long id;
        private String producto;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tipo{

        private long id;
        private String tipo;
    }


    @Override
    public String toString() {
        return
                producto.getProducto() +
                " " + marca.getMarca() +
                " " + tiposConvert(tipos.stream().map(Tipo::getTipo))+
                " " + color +
                " " + talla +
                " " + genero +
                " " + descripcion ;
    }



    private String tiposConvert(Stream<String> tipos){

        String tipo  = "";

        tipos.parallel().map(d -> tipo.concat(d)+" ");

        return tipo;

    }
}
