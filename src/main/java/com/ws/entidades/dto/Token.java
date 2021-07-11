package com.ws.entidades.dto;

import com.ws.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private String jti;
    private String sub;
    private List<String> authorities;
    private String usuario;
    private String password;
    private long codUsuario;
    private long iat;
    private long exp;
    private List<Long> id_sedes;
    private String empresa;
    private String ruc_empresa;
    private String direccion_empresa;

}
