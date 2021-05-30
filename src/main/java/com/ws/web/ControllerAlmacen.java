package com.ws.web;

import com.ws.entidades.Almacen;
import com.ws.servicios.IService;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/almacen")
@CrossOrigin("*")
public class ControllerAlmacen implements IControllerService<Almacen,String>{


    

    @Autowired
    private IService<Almacen,String> almacenService;

    @Override
    public Flowable<Almacen> buscar() {
        return almacenService.buscar();
    }

    @Override
    public Flowable<Almacen> buscar(@NotNull String id, @NotNull int estado) {
        return almacenService.buscar(Long.parseLong(id),estado);
    }

    @Override
    public Maybe<Almacen> buscarPorId(@NotNull String id) {
        return almacenService.findById(id);
    }

    @Override
    public Single<Almacen> guardar(Almacen request) {
        return almacenService.guardar(request);
    }


    @Override
    public Single<Almacen> actualizar(Almacen request, String id) {
        return almacenService.actualizar(id,request);
    }

    @Override
    public Observable<Almacen> buscarBySede(@NotNull String id) {
        return null;
    }

    @Override
    public Single<Almacen> deshabilitar(@NotNull String id) {
        return null;
    }
}
