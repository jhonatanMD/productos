package com.ws.web;

import com.ws.entidades.Modulos;
import com.ws.servicios.impl.ModulosServiceImpl;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/modulos")
@CrossOrigin("*")
public class ControllerModulo {


    @Autowired
    private ModulosServiceImpl modulosService;

    @GetMapping("/buscar")
    public Flowable<Modulos> buscar(){
        return modulosService.buscar();
    }
}
