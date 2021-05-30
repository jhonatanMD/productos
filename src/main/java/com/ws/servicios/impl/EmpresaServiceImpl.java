package com.ws.servicios.impl;

import com.ws.entidades.Empresa;
import com.ws.entidades.Sede;
import com.ws.excepciones.NotFoundException;
import com.ws.repositorio.EmpresaRepositorio;
import com.ws.servicios.IService;
import io.reactivex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements IService<Empresa,Long> {

    @Autowired
    EmpresaRepositorio empresaRepositorio;


    @Autowired
    IService<Sede,Long> serviceSede;

    @Override
    public Flowable<Empresa> buscar() {
        return empresaRepositorio.findAll();
    }

    @Override
    public Flowable<Empresa> buscar(long idSede, int estado) {
        return empresaRepositorio.findByEstado(estado);
    }

    @Override
    public Single<Empresa> guardar(Empresa empresa) {
        return empresaRepositorio.save(empresa);
    }

    @Override
    public Single<Empresa> actualizar(Long id, Empresa empresa) {
        empresa.setId(id);
        return empresaRepositorio.findById(id)
                .flatMapSingle(res -> empresaRepositorio.save(empresa));
        }

    @Override
    public Maybe<Empresa> findById(Long id) {
        return empresaRepositorio.findById(id);
    }

    @Override
    public Observable<Empresa> findByIdSede(long id_sede) {
        return serviceSede.findById(id_sede).flatMap(sede -> {
            return empresaRepositorio.findById(sede.getId_empresa());
        }).toObservable();
    }

    @Override
    public Single<Empresa> deshabilitar(Long id) {
        return null;
    }


}
