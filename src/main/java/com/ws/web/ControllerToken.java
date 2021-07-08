package com.ws.web;

import com.ws.entidades.Usuario;
import com.ws.entidades.dto.JwtResponse;
import com.ws.entidades.dto.LoginUsuario;
import com.ws.servicios.IService;
import com.ws.util.ConvercionToken;
import com.ws.util.Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/token")
@CrossOrigin("*")
public class ControllerToken {


    @Autowired
    private IService<Usuario,Long> usuarioService;

    @PostMapping("/login")
    public Single<JwtResponse> login(@RequestBody LoginUsuario.Login login) throws Exception {

        return  usuarioService.buscarUsuarioPasswod(login.getUsername(),login.getPassword())
                .map(usuario -> {
                    String token = getJWTToken(usuario);
                    JwtResponse jwtResponse = new JwtResponse();
                    jwtResponse.setToken(token);
                    jwtResponse.setDatos_usuario(usuario);
                    return jwtResponse;
                });


    }


    @GetMapping("/refresh")
    public Single<JwtResponse> login(HttpServletRequest req ) throws Exception {

        return  ConvercionToken.convercion(req).flatMap(token ->
            usuarioService.buscarUsuarioPasswod(token.getUsuario(), Util.decrypt(token.getPassword()))
                .map(usuario -> {
                    JwtResponse jwtResponse = new JwtResponse();
                    jwtResponse.setDatos_usuario(usuario);
                    return jwtResponse;
                }));


    }

    private String getJWTToken(LoginUsuario usuario) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("BYTES")
                .setSubject(usuario.getUsuario().getUsuario())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .claim("usuario",usuario.getUsuario().getUsuario())
                .claim("password",usuario.getUsuario().getPassword())
                .claim("codUsuario",usuario.getUsuario().getId())
                .claim("id_sedes",usuario.getEmpleado().getId_sede())
                .claim("empresa",usuario.getEmpresa().getNombre_empresa())
                .claim("ruc_empresa",usuario.getEmpresa().getRuc())
                .claim("direccion_empresa",usuario.getEmpresa().getDireccion())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}
