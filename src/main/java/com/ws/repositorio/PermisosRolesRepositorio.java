package com.ws.repositorio;

import com.ws.entidades.PermisosRoles;
import io.reactivex.Maybe;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface PermisosRolesRepositorio extends RxJava2CrudRepository<PermisosRoles,String> {

    Maybe<PermisosRoles> findByIdRol(long idRol);

}


