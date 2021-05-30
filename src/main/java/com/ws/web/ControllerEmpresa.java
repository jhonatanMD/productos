package com.ws.web;

import com.ws.entidades.Empresa;
import com.ws.servicios.IService;
import com.ws.util.Constantes;
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
@RequestMapping("/api/empresa")
@CrossOrigin("*")
public class ControllerEmpresa  {



    @Autowired
    private  IService<Empresa,Long> empresaService;

    @GetMapping("/buscarPorEstado/{estado}")
    Flowable<Empresa> buscar ( @PathVariable("estado")  @NotNull int estado) {
        return empresaService.buscar(Constantes.VALOR_OPCIONAL,estado);
    }

    @GetMapping("/buscar")
    Flowable<Empresa> buscar () {
        return empresaService.buscar();
    }

    @GetMapping("/buscarPorId/{id}")
    Maybe<Empresa> buscarPorId (@PathVariable("id") @IdValidacion @NotNull Long id ){
        return empresaService.findById(id);
    }
    @PostMapping("/guardar")
    Single<Empresa> guardar(@RequestBody @Valid Empresa request) {
        return empresaService.guardar(request);
    }

    @PostMapping("/actualizar/{id}")
    Single<Empresa> actualizar(@RequestBody @Valid Empresa request , @PathVariable("id")  @NotNull long id) {
        return empresaService.actualizar(id,request);
    }

    @PostMapping("/buscarPorSede/{idSede}")
    Observable<Empresa> buscarBySede( @PathVariable("idSede")  @NotNull long id) {
        return empresaService.findByIdSede(id);
    }
}
