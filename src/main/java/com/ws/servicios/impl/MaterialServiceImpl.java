package com.ws.servicios.impl;

import com.ws.entidades.Empleado;
import com.ws.entidades.Material;
import com.ws.repositorio.MaterialRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Util;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements IService<Material,Long> {

    @Autowired
    MaterialRepositorio materialRepositorio;

    @Override
    public Flowable<Material> buscar() {
        return materialRepositorio.findAll();
    }

    @Override
    public Flowable<Material> buscar(long idSede, int estado) {
        return materialRepositorio.findBySede(idSede,estado);
    }

    @Override
    public Single<Material> guardar(Material material) {
        return materialRepositorio.save(material);
    }

    @Override
    public Single<Material> actualizar(Long id, Material material) {
        material.setId(id);
        return materialRepositorio.findById(id)
                .flatMapSingle(res -> materialRepositorio.save(material));
        }

    @Override
    public Maybe<Material> findById(Long id) {
        return materialRepositorio.findById(id);
    }

    @Override
    public Observable<Material> findByIdSede(long id_sede) {
        return materialRepositorio.findBySede(id_sede);
    }

    @Override
    public Single<Material> deshabilitar(Long id) {
        return materialRepositorio.findById(id).flatMapSingle(material -> {
            material.setEstado(Util.cambiarEstado(material.getEstado()));
            return materialRepositorio.save(material);
        });
    }


}
