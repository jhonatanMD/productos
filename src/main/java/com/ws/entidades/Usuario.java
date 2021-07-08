package com.ws.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Table(value = "usuario")
public class Usuario implements Persistable<Long> {

    @Id
    @Column
    private Long id;
    @NotNull
    private String id_empleado;
    @NotNull
    private String usuario;
    @NotNull
    private String password;
    private LocalDate fecha_creacion;
    private long rol;
    private int estado;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null || id == 0 ;
    }

    public Usuario setAsNew() {
        this.newProduct = true;
        return this;
    }




}
