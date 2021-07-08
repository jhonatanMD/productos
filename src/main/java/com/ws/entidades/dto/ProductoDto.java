package com.ws.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private String id;
    private int indice;
    private int cantidad;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal precioTotal;
}
