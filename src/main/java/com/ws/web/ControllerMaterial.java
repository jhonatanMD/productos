package com.ws.web;

import com.ws.entidades.Material;
import com.ws.servicios.IService;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/material")
@CrossOrigin("*")
public class ControllerMaterial implements IControllerService<Material,Long> {


    @Autowired
    private IService <Material,Long> materialService;


    @Override
    public Flowable<Material> buscar() {
        return materialService.buscar();
    }

    @Override
    public Flowable<Material> buscar(@NotNull Long id, @NotNull int estado) {
        return materialService.buscar(id,estado);
    }

    @Override
    public Maybe<Material> buscarPorId(@NotNull Long id) {
        return  materialService.findById(id);
    }

    @Override
    public Single<Material> guardar(Material request) {
        return materialService.guardar(request);
    }

    @Override
    public Single<Material> actualizar(Material request, Long id) {
        return materialService.actualizar(id,request);
    }

    @Override
    public Observable<Material> buscarBySede(@NotNull Long id) {
        return null;
    }

    @Override
    public Single<Material> deshabilitar(@NotNull Long id) {
        return materialService.deshabilitar(id);
    }
}
