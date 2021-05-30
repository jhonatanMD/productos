package com.ws.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(value = "empresa")
public class Empresa implements Persistable<Long> {

    @Id
    @Column
    private Long id;
    @NotNull
    @NotEmpty
    private String nombre_empresa;
    @NotNull
    @NotEmpty
    private String ruc;
    @NotNull
    @NotEmpty
    private String direccion;
    private int estado;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct ||  id == null || id == 0 ;
    }

    public Empresa setAsNew() {
        this.newProduct = true;
        return this;
    }
}
