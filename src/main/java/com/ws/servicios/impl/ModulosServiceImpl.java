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



    public Flowable<Modulos> buscarModulosPorId(List<Long> ids) {
        return modulosRepositorio.findAllById(ids);
    }


    public Flowable<Modulos> buscar() {
        return modulosRepositorio.findAll();
    }


}
