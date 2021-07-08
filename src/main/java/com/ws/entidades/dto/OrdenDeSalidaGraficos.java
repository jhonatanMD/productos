package com.ws.entidades.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDeSalidaGraficos {

    private List<ProductoGrafico> productosGraficos;
    private Long ordenDeCompra;
    private Long ordenDeSalida;
    private Long productos;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductoGrafico{

        private int mes;
        private long cantidad;

    }

}
