package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Producto;
import com.ws.entidades.Tipo;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface TipoRepositorio extends RxJava2CrudRepository<Tipo,Long> ,IRepositorios<Tipo> {

    @Query("Select * from tipo where id_sede = ? and estado = ?")
    Flowable<Tipo> findBySede(long sede , int estado);

    @Query("Select * from tipo where id_sede = ? " )
    Observable<Tipo> findBySede(long sede );
}
