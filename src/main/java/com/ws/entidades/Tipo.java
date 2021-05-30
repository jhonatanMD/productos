package com.ws.entidades;

import com.ws.util.IdValidacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(value = "tipo")
public class Tipo implements Persistable<Long> {

    @Id
    @Column
    private Long id;
    @NotNull
    private String tipo;
    @NotNull
    private String descripcion;
    @IdValidacion
    private long id_sede;
    private int estado;

    @Transient
    private boolean newProduct;

    public Tipo(Long id, @NotNull String tipo, @NotNull String descripcion, long id_sede, int estado) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.id_sede = id_sede;
        this.estado = estado;
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null || id == 0 ;
    }

    public Tipo setAsNew() {
        this.newProduct = true;
        return this;
    }
}
