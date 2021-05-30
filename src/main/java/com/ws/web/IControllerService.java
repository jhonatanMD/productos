package com.ws.web;

import com.ws.util.IdValidacion;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IControllerService<T,D> {



    @GetMapping("/buscarTodo")
    Flowable<T> buscar ();

    @GetMapping("/buscar/{idSede}/{estado}")
    Flowable<T> buscar (@PathVariable("idSede")  @NotNull D id , @PathVariable("estado")   int estado);

    @GetMapping("/buscarPorId/{id}")
    Maybe<T> buscarPorId (@PathVariable("id")  @NotNull D id );

    @PostMapping("/guardar")
    Single<T> guardar(@RequestBody @Valid T request);

    @PostMapping("/actualizar/{id}")
    Single<T> actualizar(@RequestBody @Valid T request , @PathVariable("id")  @NotNull D id);

    @GetMapping("/buscarPorSede/{idSede}")
    Observable<T> buscarBySede( @PathVariable("idSede")  @NotNull D id);

    @PostMapping("/deshabilitar/{id}")
    Single<T> deshabilitar( @PathVariable("id")  @NotNull D id);

}
