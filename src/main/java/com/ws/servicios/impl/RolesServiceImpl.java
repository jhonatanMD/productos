package com.ws.servicios.impl;

import com.ws.entidades.Empleado;
import com.ws.entidades.PermisosRoles;
import com.ws.entidades.Roles;
import com.ws.repositorio.RolesRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Util;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class RolesServiceImpl implements IService<Roles,Long> {

    @Autowired
    RolesRepositorio rolesRepositorio;

    @Autowired
    PermisosRolesServiceImpl permisosRolesService;


    @Override
    public Flowable<Roles> buscar() {
        return rolesRepositorio.findAll();
    }

    @Override
    public Flowable<Roles> buscar(long idSede, int estado) {
        return rolesRepositorio.findBySede(idSede, estado);
    }

    @Override
    public Single<Roles> guardar(Roles roles) {

        return rolesRepositorio.save(roles).flatMap(role ->  permisosRolesService.guardar(PermisosRoles.builder()
                    .idRol(role.getId()).modulos(Arrays.asList())
                    .permisos(new PermisosRoles
                            .Permisos(false,false,false,false)).build())
                    .map(res -> role));
    }

    @Override
    public Single<Roles> actualizar(Long id, Roles roles) {
        roles.setId(id);
        return rolesRepositorio.findById(id)
                .flatMapSingle(res -> rolesRepositorio.save(roles));
        }

    @Override
    public Maybe<Roles> findById(Long id) {
        return rolesRepositorio.findById(id);
    }

    @Override
    public Observable<Roles> findByIdSede(long id_sede) {
        return rolesRepositorio.findBySede(id_sede);
    }

    @Override
    public Single<Roles> deshabilitar(Long id) {
        return rolesRepositorio.findById(id).flatMapSingle(roles -> {
            roles.setEstado(Util.cambiarEstado(roles.getEstado()));
            return rolesRepositorio.save(roles);
        });
    }


}
