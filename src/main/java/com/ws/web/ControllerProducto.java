package com.ws.web;

import com.ws.entidades.Empleado;
import com.ws.entidades.Empresa;
import com.ws.entidades.Producto;
import com.ws.servicios.IService;
import com.ws.util.IdValidacion;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("*")
public class ControllerProducto {

    @Autowired
    IService<Producto,Long> productoService;


    @GetMapping("/buscarTodo")
    public Flowable<Producto> buscarProductos(){
        return  productoService.buscar();
    }

    @GetMapping("/buscar/{idSede}/{estado}")
    public Flowable<Producto> buscarProductos(@PathVariable("idSede") long idSede , @PathVariable("estado") int estado){
        return  productoService.buscar(idSede,estado);
    }

    @GetMapping("/buscarProductosIdSede/{idSede}")
    public Observable<Producto> buscarProductosIdSede(@PathVariable("idSede") long idSede){
        return  productoService.findByIdSede(idSede);
    }

    @GetMapping("/buscar/{id}")
    Maybe<Producto> buscarPorId (@PathVariable("id") @IdValidacion @NotNull Long id ){
        return productoService.findById(id);
    }

    @PostMapping("/guardar")
    public Single<Producto> guardarProducto(@RequestBody Producto producto){
        return  productoService.guardar(producto);
    }

    @PostMapping("/actualizar/{id}")
    public Single<Producto> actualizarProducto(@RequestBody Producto producto, @PathVariable("id") @IdValidacion long id){

        return  productoService.actualizar(id,producto);
    }


    @PostMapping("/deshabilitar/{id}")
    Single<Producto> deshabilitar( @PathVariable("id")  @NotNull Long id){
        return productoService.deshabilitar(id);
    }
}
