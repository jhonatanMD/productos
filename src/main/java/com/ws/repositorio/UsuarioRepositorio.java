package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Usuario;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface UsuarioRepositorio extends RxJava2CrudRepository<Usuario,Long>  {

    @Query("select u.* from usuario u where u.id_empleado = ? ")
    Observable<Usuario> usuariosByEmpleado(String idSede);


    Single<Usuario> findByUsuarioAndPassword(String usuario , String password);




}
