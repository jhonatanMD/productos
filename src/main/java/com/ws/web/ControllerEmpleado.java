package com.ws.web;

import com.ws.entidades.Empleado;
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
@RequestMapping("/api/empleado")
@CrossOrigin("*")
public class ControllerEmpleado implements IControllerService<Empleado,String> {

    @Autowired
    private IService<Empleado,String> empleadoService;

    @Override
    public Flowable<Empleado> buscar() {
        return empleadoService.buscar();
    }

    @Override
    public Flowable<Empleado> buscar(@NotNull String id, @NotNull int estado) {
        return empleadoService.buscar(Long.parseLong(id),estado);
    }

    @Override
    public Maybe<Empleado> buscarPorId(@NotNull String id) {
        return empleadoService.findById(id);
    }

    @Override
    public Single<Empleado> guardar(Empleado request) {
        return empleadoService.guardar(request);
    }

    @Override
    public Single<Empleado> actualizar(Empleado request, String id) {
        return empleadoService.actualizar(id,request);
    }

    @Override
    public Observable<Empleado> buscarBySede(@NotNull String id) {
        return empleadoService.findByIdSede(Long.parseLong(id));
    }

    @Override
    public Single<Empleado> deshabilitar(@NotNull String id) {
        return empleadoService.deshabilitar(id);
    }
}
