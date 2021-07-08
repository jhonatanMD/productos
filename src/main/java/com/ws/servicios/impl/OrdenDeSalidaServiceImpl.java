package com.ws.servicios.impl;

import com.ws.entidades.OrdenDeSalida;
import com.ws.entidades.dto.AlmacenProductoConsulta;
import com.ws.entidades.dto.OrdenDeSalidaGraficos;
import com.ws.entidades.dto.OrdenDeSalidaGraficos.ProductoGrafico;
import com.ws.excepciones.ProductosNoExistentesException;
import com.ws.repositorio.OrdenDeSalidaRepository;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenDeSalidaServiceImpl {


    @Autowired
    private OrdenDeSalidaRepository repository;


    @Autowired 
    private OrdenDeCompraServiceImpl ordenDeCompraService;

    @Autowired
    private AlmacenServiceImpl almacenService;

    public Single<OrdenDeSalida> guardarOrden(OrdenDeSalida ordenDeSalida){

        ordenDeSalida.setFecha(LocalDate.now());
        return consultarCantidades(ordenDeSalida).toList().flatMapObservable(productos ->
             Observable.fromIterable(ordenDeSalida.getProductos()).flatMapMaybe(p ->
                    almacenService.findById(p.getId()).map(a -> {
                        a.setStock(a.getStock() - p.getCantidad() );
                        return a;
                    }))).flatMapMaybe(almacen ->
                almacenService.actualizarAlmacen(almacen.getId(),almacen)).toList()
                .flatMap(rq -> repository.save(ordenDeSalida));
    }

    public Maybe<OrdenDeSalida> buscarOrdenPorId(String id){
        return repository.findById(id);
    }

    public Flowable<OrdenDeSalida> buscarPorSede(Long sede){
        return repository.findBySede(sede);
    }


    public Single<OrdenDeSalidaGraficos> graficos(Long sede){
        return repository.findBySede(sede)
        .filter(orden -> orden.getFecha().getYear() == LocalDate.now().getYear())
        .groupBy(r -> r.getFecha().getMonth())
        .flatMapSingle(Flowable::toList)
        .map(orden ->  OrdenDeSalidaGraficos.ProductoGrafico.builder()
                    .mes(orden.get(0).getFecha().getMonth().getValue()).cantidad(orden.size()).build())
                    .switchIfEmpty(Flowable.fromIterable(listarFechas()))
                    .toList()
                    .map(r -> {


                        for(int i = 1 ; i <=12 ; i++){

                            if(!r.stream().parallel().map(ProductoGrafico::getMes)
                            .collect(Collectors.toList()).contains(i)){
                                r.add(ProductoGrafico.builder().mes(i).cantidad(0).build());
                            }

                        }


                        return r;
                    })
                    .map(res ->  OrdenDeSalidaGraficos.builder().productosGraficos(res).build())
                    .flatMap(orden -> {
                        return contarConsulta(sede).map(res -> {
                            orden.setOrdenDeCompra(res.getOrdenDeCompra());
                            orden.setOrdenDeSalida(res.getOrdenDeSalida());
                            orden.setProductos(res.getProductos());    
                            return orden;

                        });
                    });
    }

    private Observable<AlmacenProductoConsulta> consultarCantidades(OrdenDeSalida ordenDeSalida){

        return Observable.fromIterable(ordenDeSalida.getProductos()).flatMapMaybe(p ->
                almacenService.findAlmacenById(p.getId()).map(a -> {
                    if (!(a.getStock() >= p.getCantidad())) {
                        throw new ProductosNoExistentesException("Producto : "+a.getDescripcion() + " stock "+a.getStock());
                    }
                    return a;
                }));
    }


    private Single<OrdenDeSalidaGraficos> contarConsulta(Long sede){


        ordenDeCompraService.obtenerOrdenDeCompra(sede.intValue())
        .filter(r -> LocalDate.parse(r.getFecha()).getMonth().equals(LocalDate.now().getMonth())).count().subscribe(r -> System.out.println(r));


        return Single.zip(ordenDeCompraService.obtenerOrdenDeCompra(sede.intValue())
        .filter(r -> LocalDate.parse(r.getFecha()).getMonth()==LocalDate.now().getMonth()).count()
        , repository.findBySede(sede).filter(orden -> orden.getFecha().getMonth() == LocalDate.now().getMonth()).count(), (a,b) -> {
            return OrdenDeSalidaGraficos.builder().ordenDeCompra(a).ordenDeSalida(b).build();
        }).flatMap(res -> {
            return  almacenService.findByIdSede(sede).count().map(producto -> {
                res.setProductos(producto);
                return res;
            });
        });
    }


    private List<ProductoGrafico> listarFechas(){

        return Arrays.asList(new ProductoGrafico(1,0),new ProductoGrafico(2,0),new ProductoGrafico(3,0),
        new ProductoGrafico(4,0),new ProductoGrafico(5,0),new ProductoGrafico(6,0),new ProductoGrafico(7,0),
        new ProductoGrafico(8,0),new ProductoGrafico(9,0),new ProductoGrafico(10,0),new ProductoGrafico(11,0),
        new ProductoGrafico(12,0));
    }


}


