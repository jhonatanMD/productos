package com.ws.servicios;

import com.ws.entidades.dto.LoginUsuario;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

@Service
public interface IService<T,D> {

    Flowable<T> buscar(long idSede , int estado);

    Single<T> guardar(T request);

    Single<T> actualizar(D id ,T request);

    Maybe<T> findById(D id);

    Observable<T> findByIdSede(long id_sede);

    Single<T> deshabilitar(D id);

    default Single<T> cambiarPassword(){
        return null;
    }


    default Flowable<T> findAlmacenByProducto(long idProducto ){
        return null;
    }

    default Single<LoginUsuario> buscarUsuarioPasswod(String usuario , String password){
        return null;
    }

    default Flowable<T> buscar(){
        return null;
    }
}
