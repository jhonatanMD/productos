package com.ws.entidades.dto;

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
    private long codUsuario;
    private long iat;
    private long exp;
    private List<Long> id_sedes;

}
