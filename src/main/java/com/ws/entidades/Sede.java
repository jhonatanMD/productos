package com.ws.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(value = "sede")
public class Sede implements Persistable<Long> {

    @Id
    @Column
    private Long id;
    @NotNull
    private String sede;
    private long id_empresa;
    private int estado;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null || id == 0 ;
    }

    public Sede setAsNew() {
        this.newProduct = true;
        return this;
    }
}
