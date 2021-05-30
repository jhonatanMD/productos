package com.ws.servicios.impl;

import com.ws.entidades.Empleado;
import com.ws.entidades.Modulos;
import com.ws.entidades.Roles;
import com.ws.entidades.Usuario;
import com.ws.entidades.dto.LoginUsuario;
import com.ws.repositorio.EmpleadoRepositorio;
import com.ws.repositorio.RolesRepositorio;
import com.ws.repositorio.SedeRepositorio;
import com.ws.repositorio.UsuarioRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Constantes;
import com.ws.util.Util;
import io.reactivex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IService<Usuario,Long> {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    IService<Roles,Long> rolesService;

    @Autowired
    IService<Empleado,String> empleadoService;

    @Autowired
    PermisosRolesServiceImpl permisosRolesService;

    @Autowired
    ModulosServiceImpl modulosService;


    @Autowired
    SedeRepositorio sedeRepositorio;


    @Override
    public Flowable<Usuario> buscar() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Flowable<Usuario> buscar(long idSede, int estado) {
        return  empleadoService.buscar(idSede, estado).flatMap(empleado -> {
            return usuarioRepositorio.usuariosByEmpleado(empleado.getId())
                    .toFlowable(BackpressureStrategy.BUFFER);
        });
    }

    @Override
    public Single<Usuario> guardar(Usuario usuario) {
        String password = usuario.getPassword();
        usuario.setPassword(Util.encript(password));
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Single<Usuario> actualizar(Long id, Usuario usuario) {
        String password = usuario.getPassword();
        usuario.setId(id);
        usuario.setPassword(Util.encript(password));
        return usuarioRepositorio.findById(id)
                .flatMapSingle(res -> usuarioRepositorio.save(usuario));
        }

    @Override
    public Maybe<Usuario> findById(Long id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public Observable<Usuario> findByIdSede(long id_sede) {
        return empleadoService.findByIdSede(id_sede).flatMap(empleado -> {
           return usuarioRepositorio.usuariosByEmpleado(empleado.getId());
        });
    }

    @Override
    public Single<Usuario> deshabilitar(Long id) {
        return usuarioRepositorio.findById(id).flatMapSingle(usuario -> {
            usuario.setEstado(Util.cambiarEstado(usuario.getEstado()));
            return usuarioRepositorio.save(usuario);
        });
    }

    @Override
    public Single<LoginUsuario> buscarUsuarioPasswod(String usuario, String password) {

        return usuarioRepositorio.findByUsuarioAndPassword(usuario,Util.encript(password))
                .filter(usu -> usu.getEstado() == Constantes.ESTADO_ACTIVO)
                .flatMapSingle(usu -> {
                    LoginUsuario loginUsuario = new LoginUsuario();
                    loginUsuario.setUsuario(usu);
                    return rolesService.findById(usu.getRol())
                            .filter(roles -> roles.getEstado() == Constantes.ESTADO_ACTIVO).flatMapSingle(roles -> {
                        loginUsuario.setRol(roles);
                        return empleadoService.findById(usu.getId_empleado())
                                .filter(empleado -> empleado.getEstado() == Constantes.ESTADO_ACTIVO)
                                .flatMapSingle(empleado -> {
                            loginUsuario.setEmpleado(empleado);
                            return permisosRolesService.buscarPorRol(roles.getId())
                                    .filter(permisos -> permisos.getEstado() == Constantes.ESTADO_ACTIVO)
                                    .flatMapSingle(permisosRoles ->
                                 modulosService.buscarModulosPorId(permisosRoles.getModulos()).toList()
                                        .flatMap(modulos -> {
                                            permisosRoles.setModulos(modulos.stream().map(Modulos::getNombre).collect(Collectors.toList()));
                                            loginUsuario.setPermisosRoles(permisosRoles);
                                            return Single.just(loginUsuario);
                                        })).flatMap(res ->
                                              sedeRepositorio.findAllById(empleado.getId_sede())
                                                    .filter(sede -> sede.getEstado() == Constantes.ESTADO_ACTIVO).toList().map(sedes ->{
                                                loginUsuario.setSede(sedes);
                                                return loginUsuario;
                                            }));
                        });
                    });
                 });
    }

    @Override
    public Single<Usuario> cambiarPassword() {
        return null;
    }
}
