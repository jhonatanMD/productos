package com.ws.web;

import com.ws.entidades.Sede;
import com.ws.servicios.IService;
import com.ws.util.IdValidacion;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/sede")
@CrossOrigin("*")
public class ControllerSede {

    @Autowired
    private IService<Sede,Long> sedeService;


    @GetMapping("/buscarTodo")
    Flowable<Sede> buscar () {
        return  sedeService.buscar();
    }


    @GetMapping("/buscar/{empresa}/{estado}")
    Flowable<Sede> buscar (@PathVariable("empresa")  @NotNull long empresa,@PathVariable("estado")  @NotNull int estado) {

        return sedeService.buscar(empresa,estado);
    }

    @GetMapping("/buscar/{id}")
    Maybe<Sede> buscarPorId (@PathVariable("id") @IdValidacion @NotNull Long id ){
        return sedeService.findById(id);
    }
    @PostMapping("/guardar")
    Single<Sede> guardar(@RequestBody @Valid Sede request) {
        return sedeService.guardar(request);
    }

    @PostMapping("/actualizar/{id}")
    Single<Sede> actualizar(@RequestBody @Valid Sede request , @PathVariable("id")  @NotNull long id) {
        return sedeService.actualizar(id,request);
    }

    @GetMapping("/buscarPorSede/{idSede}")
    Observable<Sede> buscarBySede( @PathVariable("idSede")  @NotNull long id) {
        return sedeService.findByIdSede(id);
    }

    @PostMapping("/deshabilitar/{id}")
    Single<Sede> deshabilitar( @PathVariable("id")  @NotNull Long id){

        return sedeService.deshabilitar(id);
    }

}
