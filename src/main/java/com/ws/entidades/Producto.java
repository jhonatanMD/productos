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
@Table(value = "producto")
public class Producto  implements Persistable<Long> {
    @Id
    @Column
    private Long id;
    private long id_sede;
    @NotNull
    private String nombre;
    @NotNull
    private String descripcion;
    private int estado;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null || id == 0 ;
    }

    public Producto setAsNew() {
        this.newProduct = true;
        return this;
    }
}
