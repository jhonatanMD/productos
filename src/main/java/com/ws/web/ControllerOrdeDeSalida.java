package com.ws.web;

import com.ws.entidades.OrdenDeSalida;
import com.ws.entidades.dto.OrdenDeSalidaGraficos;
import com.ws.servicios.impl.OrdenDeSalidaServiceImpl;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ordenSalida")
public class ControllerOrdeDeSalida {


    @Autowired
    private OrdenDeSalidaServiceImpl ordenDeSalidaService;

    @PostMapping("/guardar")
    public Single<OrdenDeSalida> guardar(@RequestBody OrdenDeSalida ordenDeSalida){
        return ordenDeSalidaService.guardarOrden(ordenDeSalida);
    }


    @GetMapping("/buscarPorId/{id}")
    public Maybe<OrdenDeSalida> ordenDeSalidaPorId(@PathVariable("id") String id){
        return ordenDeSalidaService.buscarOrdenPorId(id);
    }

    @GetMapping("/buscar/{sede}")
    public Flowable<OrdenDeSalida> ordenDeSalidaPorSede(@PathVariable("sede") Long sede){

        return ordenDeSalidaService.buscarPorSede(sede);
    }


    @GetMapping("/graficos/{sede}")
    public Single<OrdenDeSalidaGraficos> graficos(@PathVariable("sede") Long sede){

                return  ordenDeSalidaService.graficos(sede);
               
    }


}
