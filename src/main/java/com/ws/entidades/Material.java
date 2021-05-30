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
@Table(value = "material")
public class Material implements Persistable<Long> {

    @Id
    @Column
    private Long id;
    @NotNull
    @NotEmpty
    private String material;
    @NotNull
    @NotEmpty
    private String descripcion;
    private long id_sede;
    private int estado;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null || id == 0 ;
    }

    public Material setAsNew() {
        this.newProduct = true;
        return this;
    }
}
