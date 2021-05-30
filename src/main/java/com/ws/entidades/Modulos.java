package com.ws.entidades;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("modulos")
public class Modulos implements Persistable<Integer> {

    @Id
    @Column
    private Integer id;
    private String nombre;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null || id == 0 ;
    }

    public Modulos setAsNew() {
        this.newProduct = true;
        return this;
    }
}
