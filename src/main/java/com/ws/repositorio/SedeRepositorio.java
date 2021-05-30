package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Roles;
import com.ws.entidades.Sede;
import com.ws.entidades.Tipo;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

import java.util.List;

public interface SedeRepositorio extends RxJava2CrudRepository<Sede,Long> , IRepositorios<Sede>   {



    @Query("Select * from sede where id_empresa = ? and estado = ?" )
    Flowable<Sede> findByEmpresa(long empresa , int estado );



}
