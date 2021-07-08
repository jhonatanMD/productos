package com.ws.servicios;

import com.ws.entidades.dto.AlmacenProductoConsulta;
import com.ws.entidades.dto.LoginUsuario;
import com.ws.entidades.dto.ProductoConsulta;
import com.ws.entidades.dto.StockProductos;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

import java.util.List;

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


    default Maybe<List<AlmacenProductoConsulta>> buscarAlmacen(long idSede , int estado){return null;}


    default Maybe<AlmacenProductoConsulta> findByIdAlmacen(String id){return null;}


    default  Maybe<AlmacenProductoConsulta> actualizarAlmacen( D id ,T request){return null;}

    default Maybe<AlmacenProductoConsulta> guardarAlmacen(T request){return null;}

    default  Observable<StockProductos> productosConStockMin(Long idSede){
        return null;
    }
}
