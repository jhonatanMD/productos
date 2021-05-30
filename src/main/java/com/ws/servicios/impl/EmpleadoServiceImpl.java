package com.ws.servicios.impl;

import com.ws.entidades.Empleado;
import com.ws.repositorio.EmpleadoRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Constantes;
import com.ws.util.Util;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Component
public class EmpleadoServiceImpl implements IService<Empleado,String> {

    @Autowired
    EmpleadoRepositorio empleadoRepositorio;

    @Override
    public Flowable<Empleado> buscar() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Flowable<Empleado> buscar(long idSede, int estado) {
        return empleadoRepositorio.findBySede(idSede,estado);
    }

    @Override
    public Single<Empleado> guardar(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public Single<Empleado> actualizar(String id, Empleado empleado) {
        empleado.setId(id);
        return empleadoRepositorio.findById(id)
                .flatMapSingle(res -> empleadoRepositorio.save(empleado));
        }

    @Override
    public Maybe<Empleado> findById(String id) {

        return empleadoRepositorio.findById(id);
    }

    @Override
    public Observable<Empleado> findByIdSede(long id_sede) {
        return empleadoRepositorio.findBySede(id_sede).toObservable();

    }

    @Override
    public Single<Empleado> deshabilitar(String id) {
        return empleadoRepositorio.findById(id).flatMapSingle(empleado -> {
            empleado.setEstado(Util.cambiarEstado(empleado.getEstado()));
            return empleadoRepositorio.save(empleado);
        });
    }


}
