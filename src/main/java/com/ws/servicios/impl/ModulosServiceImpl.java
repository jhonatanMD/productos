package com.ws.servicios.impl;

import com.ws.entidades.Modulos;
import com.ws.repositorio.ModulosRepositorio;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModulosServiceImpl {


   @Autowired
   private ModulosRepositorio modulosRepositorio;



    public Flowable<Modulos> buscarModulosPorId(List<String> ids) {
        return modulosRepositorio.findAllById(ids.stream().map(Integer::parseInt).collect(Collectors.toList()));
    }


    public Flowable<Modulos> buscar() {
        return modulosRepositorio.findAll();
    }


}
