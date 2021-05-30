package com.ws.entidades;

import com.ws.util.IdValidacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Table(value = "roles")
public class Roles implements Persistable<Long> {

    @Id
    private Long id;
    @IdValidacion
    private long id_sede;
    @NotNull
    private String rol;
    @NotNull
    private String descripcion;
    private int estado;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null || id == 0  ;
    }

    public Roles setAsNew() {
        this.newProduct = true;
        return this;
    }

}
