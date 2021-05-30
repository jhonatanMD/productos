package com.ws.web;

import com.ws.entidades.Tipo;
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
@RequestMapping("/api/tipo")
@CrossOrigin("*")
public class ControllerTipo implements IControllerService<Tipo,Long> {

    @Autowired
    IService<Tipo,Long> tipoService;

    @Override
    public Flowable<Tipo> buscar() {
        return tipoService.buscar();
    }

    @Override
    public Flowable<Tipo> buscar(@NotNull Long id, @NotNull int estado) {

        return tipoService.buscar(id,estado);
    }

    @Override
    public Maybe<Tipo> buscarPorId(@NotNull Long id) {
        return tipoService.findById(id);
    }

    @Override
    public Single<Tipo> guardar(Tipo request) {
        return tipoService.guardar(request);
    }

    @Override
    public Single<Tipo> actualizar(Tipo request, Long id) {
        return tipoService.actualizar(id,request);
    }

    @Override
    public Observable<Tipo> buscarBySede(@NotNull Long id) {
        return null;
    }

    @Override
    public Single<Tipo> deshabilitar(@NotNull Long id) {
        return tipoService.deshabilitar(id);
    }
}
