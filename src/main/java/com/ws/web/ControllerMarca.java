package com.ws.web;

import com.ws.entidades.Marca;
import com.ws.servicios.IService;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/marca")
@CrossOrigin("*")
public class ControllerMarca implements IControllerService<Marca,Long> {

    @Autowired
    private IService<Marca,Long> marcaService;


    @Override
    public Flowable<Marca> buscar() {
        return marcaService.buscar();
    }

    @Override
    public Flowable<Marca> buscar(@NotNull Long id,  int estado) {
        return marcaService.buscar(id,estado);
    }

    @Override
    public Maybe<Marca> buscarPorId(@NotNull Long id) {
        return marcaService.findById(id);
    }

    @Override
    public Single<Marca> guardar( Marca request) {
        return marcaService.guardar(request);
    }

    @Override
    public Single<Marca> actualizar(Marca request, Long id) {
        return marcaService.actualizar(id,request);
    }

    @Override
    public Observable<Marca> buscarBySede(@NotNull Long id) {
        return marcaService.findByIdSede(id);
    }

    @Override
    public Single<Marca> deshabilitar(@NotNull Long id) {
        return marcaService.deshabilitar(id);
    }
}
