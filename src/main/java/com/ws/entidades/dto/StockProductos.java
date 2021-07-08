package com.ws.entidades.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockProductos {
    
    private String producto;
    private long stock;
    private long stockMinimo;
}
