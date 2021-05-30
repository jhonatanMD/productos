package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Marca;
import io.reactivex.Flowable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface MarcaRepositorio extends RxJava2CrudRepository<Marca,Long> ,IRepositorios<Marca>  {


    @Query("Select * from marca where id_sede = ? and estado = ?")
    Flowable<Marca> findBySede(long sede , int estado);
}
