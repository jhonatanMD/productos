package com.ws.servicios.impl;

import com.ws.entidades.Empleado;
import com.ws.entidades.Tipo;
import com.ws.repositorio.TipoRepositorio;
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
public class TipoServiceImpl implements IService<Tipo,Long> {

    @Autowired
    TipoRepositorio tipoRepositorio;


    @Override
    public Flowable<Tipo> buscar() {
        return tipoRepositorio.findAll();
    }

    @Override
    public Flowable<Tipo> buscar(long idSede, int estado) {
        return tipoRepositorio.findBySede(idSede,estado);
    }

    @Override
    public Single<Tipo> guardar(Tipo tipo) {
        return tipoRepositorio.save(tipo);
    }

    @Override
    public Single<Tipo> actualizar(Long id, Tipo tipo) {
        tipo.setId(id);
        return tipoRepositorio.findById(id)
                .flatMapSingle(res -> tipoRepositorio.save(tipo));
        }

    @Override
    public Maybe<Tipo> findById(Long id) {
        return tipoRepositorio.findById(id);
    }

    @Override
    public Observable<Tipo> findByIdSede(long id_sede) {
        return tipoRepositorio.findBySede(id_sede);
    }

    @Override
    public Single<Tipo> deshabilitar(Long id) {
        return tipoRepositorio.findById(id).flatMapSingle(tipo -> {
            tipo.setEstado(Util.cambiarEstado(tipo.getEstado()));
            return tipoRepositorio.save(tipo);
        });
    }
}
