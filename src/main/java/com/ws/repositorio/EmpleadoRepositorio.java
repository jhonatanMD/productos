package com.ws.repositorio;

import com.ws.entidades.Empleado;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;


public interface EmpleadoRepositorio extends RxJava2CrudRepository<Empleado,String> , IRepositorios<Empleado> {

    @Query(value = "{'id_sede' : ?0 ,'estado' : ?1}")
    Flowable<Empleado> findBySede(long sede , int estado);
    @Query(value = "{'id_sede':?0}")
    Flowable<Empleado> findBySede(long sede);

}
