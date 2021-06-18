package com.ws.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ws.util.IdValidacion;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document(collection = "Almacen")
public class Almacen {

    @Id
    private String id;
    @IdValidacion(message = "Id Producto no puede ser 0")
    private long id_producto;
    @IdValidacion(message = "Id Marca no puede ser 0")
    private long id_marca;

    private List<Long> id_tipo;
    @IdValidacion(message = "Id Sede no puede ser 0")
    private long id_sede;
    @NotEmpty
    private String color;
    @NotEmpty
    private String talla;
    @NotEmpty
    private String genero;
    @NotNull
    private BigDecimal precio;
    @NotEmpty
    private String descripcion;
    private long stock;
    @NotNull
    private long minimo_stock;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fecha_ingreso;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fecha_actualizacion;
    private long id_usuario_mantenimiento;

    private int estado;




}
