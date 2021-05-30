package com.ws.entidades;

import com.ws.util.IdValidacion;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Document(collection = "Empleado")
public class Empleado {

    @Id
    @Column
    private String id;
    @NotNull
    @NotEmpty(message = "Nombre no puede estar vacío")
    private String nombre;
    @NotNull
    @NotEmpty(message = "Apellido Paterno no puede estar vacío")
    private String ape_paterno;
    @NotNull
    @NotEmpty(message = "Apellido Materno no puede estar vacío")
    private String ape_materno;
    @NotNull
    @NotEmpty(message = "Correo Electronico no puede estar vacío")
    private String correo_electronico;
    @NotNull
    @NotEmpty(message = "Celular no puede estar vacío")
    private String numero_celular;
    @NotNull
    @NotEmpty(message = "Dni no puede estar vacío")
    private String dni;
    private List<Long> id_sede;
    private int estado;


}
