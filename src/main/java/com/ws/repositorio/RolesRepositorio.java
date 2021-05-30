package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Marca;
import com.ws.entidades.Roles;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface RolesRepositorio extends RxJava2CrudRepository<Roles,Long> ,IRepositorios <Roles>{


    @Query("Select * from roles where id_sede = ? and estado = ?" )
    Flowable<Roles> findBySede(long sede , int estado);


    @Query("Select * from roles where id_sede = ? " )
    Observable<Roles> findBySede(long sede);
    Single<Roles> findByRol(String rol);


}
