package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Producto;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface ProductoRepositorio extends RxJava2CrudRepository<Producto,Long> , IRepositorios<Producto>  {

    @Query("Select * from producto where id_sede = ? and estado = ?")
    Flowable<Producto> findBySede(long sede , int estado);

    @Query("SELECT * FROM producto where nombre like concat(?,'%')")
    Maybe<Producto> findByNombre(String nombre);
}
