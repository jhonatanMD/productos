package com.ws.repositorio;

import com.ws.entidades.Empleado;
import io.reactivex.Flowable;

public interface IRepositorios <T>{

    Flowable<T> findByEstado(int estado);


}
