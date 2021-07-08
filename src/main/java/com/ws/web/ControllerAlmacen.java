package com.ws.web;

import com.ws.entidades.Almacen;
import com.ws.entidades.dto.AlmacenProductoConsulta;
import com.ws.entidades.dto.ProductoConsulta;
import com.ws.entidades.dto.StockProductos;
import com.ws.servicios.IService;
import com.ws.util.ConvercionToken;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/almacen")
@CrossOrigin("*")
public class ControllerAlmacen {

    @Autowired
    private IService<Almacen,String> almacenService;


    @GetMapping("/buscar/{idSede}/{estado}")
    public Maybe<List<AlmacenProductoConsulta>>buscar(@PathVariable("idSede")  @NotNull long id , @PathVariable("estado")   int estado) {
        return almacenService.buscarAlmacen(id,estado);
    }

    @GetMapping("/buscarPorId/{id}")
    public Maybe<AlmacenProductoConsulta> buscarPorId(@PathVariable("id") @NotNull String id) {
        return almacenService.findByIdAlmacen(id);
    }

    @PostMapping("/guardar")
    public Maybe<AlmacenProductoConsulta>  guardar(HttpServletRequest req ,@RequestBody @Valid  Almacen request) {

        return ConvercionToken.convercion(req).flatMapMaybe(token -> {
            request.setId_usuario_mantenimiento(token.getCodUsuario());
            return almacenService.guardarAlmacen(request);
        });


    }

    @PostMapping("/actualizar/{id}")
    public Maybe<AlmacenProductoConsulta>  actualizar(HttpServletRequest req ,@RequestBody @Valid Almacen request, @PathVariable("id")  @NotNull String id) {

        return ConvercionToken.convercion(req).flatMapMaybe(token -> {
            request.setId_usuario_mantenimiento(token.getCodUsuario());
            return almacenService.actualizarAlmacen(id, request);
        });
    }



    @PostMapping("/deshabilitar/{id}")
    public Single<Almacen> deshabilitar(@PathVariable("id") @NotNull String id) {
        return almacenService.deshabilitar(id);
    }

    @GetMapping("/stockProductos/{idSede}")
    public Observable<StockProductos> stockProductos(@PathVariable("idSede") @NotNull Long idSede) {
        return almacenService.productosConStockMin(idSede);
    }

    
}
