package com.ws.entidades;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "PermisosRoles")
public class PermisosRoles {

    @Id
    private String id;
    private long idRol;
    private List<Modulos> modulos;
    @NotNull
    private Permisos permisos;
    private int estado;



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Permisos{

        private boolean insertar;
        private boolean consultar;
        private boolean actualizar;
        private boolean eliminar;
    }
}
