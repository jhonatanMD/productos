package com.ws.entidades.dto;

import com.ws.entidades.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class LoginUsuario {

    private Usuario usuario;
    private Empleado empleado;
    private Roles rol;
    private PermisosRoles permisosRoles;
    private List<Sede> sede;


    @Getter
    @Setter
    public static class Login{
        @NotNull
        private String username;
        @NotNull
        private String password;
    }
}
