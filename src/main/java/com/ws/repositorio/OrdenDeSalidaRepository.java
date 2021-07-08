package com.ws.repositorio;

import com.ws.entidades.OrdenDeSalida;
import io.reactivex.Flowable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface OrdenDeSalidaRepository extends RxJava2CrudRepository<OrdenDeSalida , String> {


    Flowable<OrdenDeSalida> findBySede(Long sede);
}
