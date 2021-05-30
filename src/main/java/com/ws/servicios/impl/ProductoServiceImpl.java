package com.ws.servicios.impl;

import com.ws.entidades.Producto;
import com.ws.repositorio.ProductoRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Util;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IService<Producto,Long>  {

    @Autowired
    ProductoRepositorio productoRepositorio;


    @Override
    public Flowable<Producto> buscar() {
        return productoRepositorio.findAll();
    }

    @Override
    public Flowable<Producto> buscar(long idSede, int estado) {
        return productoRepositorio.findBySede(idSede,estado);
    }

    @Override
    public Single<Producto> guardar(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public Single<Producto> actualizar(Long id, Producto producto) {
        producto.setId(id);
        return productoRepositorio.findById(id)
                .flatMapSingle(res -> productoRepositorio.save(producto));
        }

    @Override
    public Maybe<Producto> findById(Long id) {
        return productoRepositorio.findById(id);
    }

    @Override
    public Observable<Producto> findByIdSede(long id_sede) {
        return productoRepositorio.findAll()
                .filter(producto -> producto.getId_sede() == id_sede).toObservable();
    }

    @Override
    public Single<Producto> deshabilitar(Long id) {
        return productoRepositorio.findById(id).flatMapSingle(producto -> {
            producto.setEstado(Util.cambiarEstado(producto.getEstado()));
            return productoRepositorio.save(producto);
        });
    }
}
