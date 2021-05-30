package com.ws.repositorio;

import com.ws.entidades.Almacen;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface AlmacenRepositorio extends RxJava2CrudRepository<Almacen,String>{


    @Query(value = "{'id_producto' : ?0}")
    Flowable<Almacen> buscarAlmacenPorProducto(long idProducto);

    @Query(value = "{'id_sede' : ?0 ,'estado' : ?1}")
    Flowable<Almacen> findBySede(long sede , int estado);

    @Query(value = "{'id_sede':?0}")
    Observable<Almacen> findBySede(long sede);
}

