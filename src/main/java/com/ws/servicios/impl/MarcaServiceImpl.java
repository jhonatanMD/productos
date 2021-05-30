package com.ws.servicios.impl;

import com.ws.entidades.Empleado;
import com.ws.entidades.Marca;
import com.ws.repositorio.MarcaRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Constantes;
import com.ws.util.Util;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements IService<Marca,Long> {

    @Autowired
    MarcaRepositorio marcaRepositorio;

    @Override
    public Flowable<Marca> buscar() {
        return marcaRepositorio.findAll();
    }

    @Override
    public Flowable<Marca> buscar(long idSede, int estado) {
        return marcaRepositorio.findBySede(idSede,estado);
    }

    @Override
    public Single<Marca> guardar(Marca marca) {
        return marcaRepositorio.save(marca);
    }

    @Override
    public Single<Marca> actualizar(Long id, Marca marca) {
        marca.setId(id);
        return marcaRepositorio.findById(id)
                .flatMapSingle(res -> marcaRepositorio.save(marca));
        }

    @Override
    public Maybe<Marca> findById(Long id) {
        return marcaRepositorio.findById(id);
    }

    @Override
    public Observable<Marca> findByIdSede(long id_sede) {
        return marcaRepositorio.findAll()
                .filter(producto -> producto.getId_sede() == id_sede).toObservable();
    }

    @Override
    public Single<Marca> deshabilitar(Long id) {
        return marcaRepositorio.findById(id).flatMapSingle(marca -> {
            marca.setEstado(Util.cambiarEstado(marca.getEstado()));
            return marcaRepositorio.save(marca);
        });
    }
}
