package com.ws.web;

import com.ws.entidades.Roles;
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
@RequestMapping("/api/roles")
@CrossOrigin("*")
public class ControllerRoles implements IControllerService<Roles,Long>{

    @Autowired
    private IService<Roles,Long> rolesService;


    @Override
    public Flowable<Roles> buscar() {
        return rolesService.buscar();
    }

    @Override
    public Flowable<Roles> buscar(@NotNull Long id, @NotNull int estado) {
        return rolesService.buscar(id,estado);
    }

    @Override
    public Maybe<Roles> buscarPorId(@NotNull Long id) {
        return rolesService.findById(id);
    }

    @Override
    public Single<Roles> guardar(Roles request) {
        return rolesService.guardar(request);
    }

    @Override
    public Single<Roles> actualizar(Roles request, Long id) {
        return rolesService.actualizar(id,request);
    }

    @Override
    public Observable<Roles> buscarBySede(@NotNull Long id) {
        return null;
    }

    @Override
    public Single<Roles> deshabilitar(@NotNull Long id) {
        return rolesService.deshabilitar(id);
    }
}
