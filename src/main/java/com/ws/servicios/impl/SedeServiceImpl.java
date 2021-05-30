package com.ws.servicios.impl;

import com.ws.entidades.Empleado;
import com.ws.entidades.Sede;
import com.ws.repositorio.SedeRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Util;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedeServiceImpl implements IService<Sede,Long> {

    @Autowired
    SedeRepositorio sedeRepositorio;


    @Override
    public Flowable<Sede> buscar() {
        return sedeRepositorio.findAll();
    }

    @Override
    public Flowable<Sede> buscar(long idEmpresa, int estado) {
        return sedeRepositorio.findByEmpresa(idEmpresa , estado);
    }

    @Override
    public Single<Sede> guardar(Sede sede) {
        return sedeRepositorio.save(sede);
    }

    @Override
    public Single<Sede> actualizar(Long id, Sede sede) {
        sede.setId(id);
        return sedeRepositorio.findById(id)
                .flatMapSingle(res -> sedeRepositorio.save(sede));
        }

    @Override
    public Maybe<Sede> findById(Long id) {
        return sedeRepositorio.findById(id);
    }

    @Override
    public Observable<Sede> findByIdSede(long id_sede) {
        return sedeRepositorio.findById(id_sede).toObservable();
    }

    @Override
    public Single<Sede> deshabilitar(Long id) {
        return sedeRepositorio.findById(id).flatMapSingle(sede -> {
            sede.setEstado(Util.cambiarEstado(sede.getEstado()));
            return sedeRepositorio.save(sede);
        });
    }


}
