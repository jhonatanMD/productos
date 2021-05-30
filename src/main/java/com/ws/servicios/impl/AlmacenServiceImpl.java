package com.ws.servicios.impl;

import com.ws.entidades.Almacen;
import com.ws.repositorio.AlmacenRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Util;
import io.reactivex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlmacenServiceImpl implements IService<Almacen,String> {

    @Autowired
    AlmacenRepositorio almacenRepositorio;

    @Override
    public Flowable<Almacen> buscar() {
        return almacenRepositorio.findAll();
    }

    @Override
    public Flowable<Almacen> buscar(long idSede, int estado) {

        return almacenRepositorio.findBySede(idSede,estado);
    }

    @Override
    public Single<Almacen> guardar(Almacen request) {
        return almacenRepositorio.save(request);
    }

    @Override
    public Single<Almacen> actualizar(String id, Almacen request) {
        request.setId(id);
        return almacenRepositorio.findById(id)
                .flatMapSingle(res -> almacenRepositorio.save(request));
    }

    @Override
    public Maybe<Almacen> findById(String id) {
        return almacenRepositorio.findById(id);
    }

    @Override
    public Observable<Almacen> findByIdSede(long id_sede) {
        return almacenRepositorio.findBySede(id_sede);
    }

    @Override
    public Flowable<Almacen> findAlmacenByProducto(long idProducto) {

        return almacenRepositorio.buscarAlmacenPorProducto(idProducto);
    }

    @Override
    public Single<Almacen> deshabilitar(String id) {
        return almacenRepositorio.findById(id).flatMapSingle(almacen -> {
            almacen.setEstado(Util.cambiarEstado(almacen.getEstado()));
            return almacenRepositorio.save(almacen);
        });
    }
}
