package com.ws.servicios.impl;

import com.ws.entidades.Modulos;
import com.ws.entidades.PermisosRoles;
import com.ws.repositorio.PermisosRolesRepositorio;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermisosRolesServiceImpl {

    @Autowired
    PermisosRolesRepositorio permisosRolesRepositorio;


    public Maybe<PermisosRoles> buscarPorRol(long idRol){
        return permisosRolesRepositorio.findByIdRol(idRol);
    }


    public Single<PermisosRoles> guardar(PermisosRoles permisosRoles){
        return permisosRolesRepositorio.save(permisosRoles);
    }

    public Single<PermisosRoles> actualizar(PermisosRoles permisosRoles){
        return permisosRolesRepositorio.findByIdRol(permisosRoles.getIdRol())
                .flatMapSingle(permisos -> {
                    permisosRoles.setId(permisos.getId());
                    return permisosRolesRepositorio.save(permisosRoles);
                });
    }

}
