package com.ws.web;

import com.ws.entidades.Usuario;
import com.ws.entidades.dto.LoginUsuario;
import com.ws.servicios.IService;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/usuario")
public class ControllerUsuario implements IControllerService<Usuario,Long> {

    @Autowired
    private IService<Usuario,Long> usuarioService;

    @Override
    public Flowable<Usuario> buscar() {
        return usuarioService.buscar();
    }

    @Override
    public Flowable<Usuario> buscar(@NotNull Long id, @NotNull int estado) {
        return usuarioService.buscar(id,estado);
    }

    @Override
    public Maybe<Usuario> buscarPorId(@NotNull Long id) {
        return usuarioService.findById(id);
    }

    @Override
    public Single<Usuario> guardar(Usuario request) {
        return usuarioService.guardar(request);
    }

    @Override
    public Single<Usuario> actualizar(Usuario request, Long id) {
        return usuarioService.actualizar(id,request);
    }

    @Override
    public Observable<Usuario> buscarBySede(@NotNull Long id) {
        return usuarioService.findByIdSede(id);
    }

    @Override
    public Single<Usuario> deshabilitar(@NotNull Long id) {
        return usuarioService.deshabilitar(id);
    }


    @PostMapping("/logeo")
    public Single<LoginUsuario> logeo(@RequestBody LoginUsuario.Login login){

        return usuarioService.buscarUsuarioPasswod(login.getUsername(),login.getPassword());
    }
}
