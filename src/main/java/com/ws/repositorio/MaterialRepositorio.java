package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Material;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface MaterialRepositorio extends RxJava2CrudRepository<Material,Long> ,IRepositorios<Material> {

    @Query("Select * from material where id_sede = ? and estado = ?")
    Flowable<Material> findBySede(long sede , int estado);


    @Query("Select * from material where id_sede = ? ")
    Observable<Material> findBySede(long sede );
}
