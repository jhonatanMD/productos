package com.ws.servicios.impl;

import com.ws.entidades.Almacen;
import com.ws.entidades.Marca;
import com.ws.entidades.Tipo;
import com.ws.entidades.dto.AlmacenProductoConsulta;
import com.ws.entidades.dto.ProductoConsulta;
import com.ws.entidades.dto.StockProductos;
import com.ws.repositorio.AlmacenRepositorio;
import com.ws.repositorio.ConsultaVozRepositorio;
import com.ws.repositorio.ProductoRepositorio;
import com.ws.repositorio.TipoRepositorio;
import com.ws.servicios.IService;
import com.ws.util.Util;
import io.reactivex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlmacenServiceImpl implements IService<Almacen,String> {

    @Autowired
    AlmacenRepositorio almacenRepositorio;


    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private ConsultaVozRepositorio consultaVozRepositorio;

    @Autowired
    private IService<Marca,Long> marcaService;

    @Autowired
    private TipoRepositorio tipoRepositorio;



    @Override
    public Flowable<Almacen> buscar() {
        return almacenRepositorio.findAll();
    }

    @Override
    public Flowable<Almacen> buscar(long idSede, int estado) {

        return almacenRepositorio.findBySede(idSede,estado);
    }

    @Override
    public Maybe<AlmacenProductoConsulta> actualizarAlmacen(String id, Almacen request) {
        request.setFecha_actualizacion(LocalDate.now());
        request.setId(id);
        return almacenRepositorio.findById(id)
                .flatMapSingle(res -> {
                    request.setFecha_ingreso(res.getFecha_ingreso());
                    return almacenRepositorio.save(request);
                }).flatMapMaybe(res -> findAlmacenById(res.getId()));
    }

    @Override
    public Maybe<AlmacenProductoConsulta> guardarAlmacen(Almacen request) {
        request.setFecha_ingreso(LocalDate.now());
        return almacenRepositorio.save(request).flatMapMaybe(res -> findAlmacenById(res.getId()));
    }


    @Override
    public Single<Almacen> guardar(Almacen request) {
        request.setFecha_ingreso(LocalDate.now());
        return almacenRepositorio.save(request);
    }

    @Override
    public Single<Almacen> actualizar(String id, Almacen request) {
        request.setFecha_actualizacion(LocalDate.now());
        request.setId(id);
        return almacenRepositorio.findById(id)
                .flatMapSingle(res -> {
                    request.setFecha_ingreso(res.getFecha_ingreso());
                   return almacenRepositorio.save(request);
                });
    }

    @Override
    public Maybe<Almacen> findById(String id) {
        return almacenRepositorio.findById(id);
    }

    @Override
    public Maybe<AlmacenProductoConsulta> findByIdAlmacen(String id) {
        return findAlmacenById(id);
    }

    @Override
    public Observable<Almacen> findByIdSede(long id_sede) {

        return almacenRepositorio.findBySede(id_sede);
    }

    @Override
    public Flowable<Almacen> findAlmacenByProducto(long idProducto) {

        return almacenRepositorio.buscarAlmacenPorProducto(idProducto);
    }

    @Override
    public Single<Almacen> deshabilitar(String id) {
        return almacenRepositorio.findById(id).flatMapSingle(almacen -> {
            almacen.setEstado(Util.cambiarEstado(almacen.getEstado()));
            return almacenRepositorio.save(almacen);
        });
    }

    @Override
    public Maybe<List<AlmacenProductoConsulta>> buscarAlmacen(long idSede, int estado) {
        return almacenRepositorio.findBySede(idSede,estado)
                        .flatMap(almacen -> {
                            AlmacenProductoConsulta productoConsulta = new AlmacenProductoConsulta();
                            productoConsulta.setIdAlmacen(almacen.getId());
                            productoConsulta.setFecha_ingreso(almacen.getFecha_ingreso());
                            productoConsulta.setFecha_actualizacion(almacen.getFecha_actualizacion());
                            productoConsulta.setColor(almacen.getColor());
                            productoConsulta.setGenero(almacen.getGenero());
                            productoConsulta.setDescripcion(almacen.getDescripcion());
                            productoConsulta.setStock(almacen.getStock());
                            productoConsulta.setMinimo_stock(almacen.getMinimo_stock());
                            productoConsulta.setPrecio(almacen.getPrecio());
                            productoConsulta.setTalla(almacen.getTalla());
                            return productoRepositorio.findById(almacen.getId_producto()).flatMapObservable(producto -> {
                                productoConsulta.setProducto(new AlmacenProductoConsulta.Producto(producto.getId(),producto.getNombre()));
                                  return marcaService.findById(almacen.getId_marca()).toObservable().map(marca -> {
                                      productoConsulta.setMarca(new AlmacenProductoConsulta.Marca(marca.getId(),marca.getMarca()));
                                    return productoConsulta;
                                });
                            }).toFlowable(BackpressureStrategy.BUFFER).flatMapMaybe(r -> tipoRepositorio.findAllById(almacen.getId_tipo())
                                    .toList().toMaybe().map(res -> {
                                        productoConsulta.setTipos( res.stream().map(tipo -> {
                                            return new AlmacenProductoConsulta.Tipo(tipo.getId(),tipo.getTipo());
                                        }).collect(Collectors.toList()));
                                        productoConsulta.setDisplayName(productoConsulta.toString());
                                        return productoConsulta;
                                    }));
                        }).toList().toMaybe();

    }

    @Override
    public Observable<StockProductos> productosConStockMin(Long idSede){
        return almacenRepositorio.findBySede(idSede)
        .skip(5)
        .filter(d -> d.getStock() <= d.getMinimo_stock())
        .flatMapMaybe(almacen -> {
            return findAlmacenById(almacen.getId()).map(producto -> {
                return StockProductos.builder().producto(producto.getDisplayName()).stockMinimo(almacen.getMinimo_stock()).stock(almacen.getStock()).build();
            });
        });
    }



    public Maybe<AlmacenProductoConsulta> findAlmacenById(String id){

        return almacenRepositorio.findById(id).flatMap(almacen -> {
            AlmacenProductoConsulta productoConsulta = new AlmacenProductoConsulta();
            productoConsulta.setIdAlmacen(almacen.getId());
            productoConsulta.setFecha_ingreso(almacen.getFecha_ingreso());
            productoConsulta.setFecha_actualizacion(almacen.getFecha_actualizacion());
            productoConsulta.setColor(almacen.getColor());
            productoConsulta.setGenero(almacen.getGenero());
            productoConsulta.setDescripcion(almacen.getDescripcion());
            productoConsulta.setStock(almacen.getStock());
            productoConsulta.setMinimo_stock(almacen.getMinimo_stock());
            productoConsulta.setPrecio(almacen.getPrecio());
            productoConsulta.setTalla(almacen.getTalla());
            return productoRepositorio.findById(almacen.getId_producto()).flatMap(producto -> {
                productoConsulta.setProducto(new AlmacenProductoConsulta.Producto(producto.getId(),producto.getNombre()));
                return marcaService.findById(almacen.getId_marca()).map(marca -> {
                    productoConsulta.setMarca(new AlmacenProductoConsulta.Marca(marca.getId(),marca.getMarca()));
                    return productoConsulta;
                });
            }).flatMap(r -> tipoRepositorio.findAllById(almacen.getId_tipo())
                    .toList().toMaybe().map(res -> {

                        productoConsulta.setTipos( res.stream().map(tipo -> {
                            return new AlmacenProductoConsulta.Tipo(tipo.getId(),tipo.getTipo());
                        }).collect(Collectors.toList()));
                        productoConsulta.setDisplayName(productoConsulta.toString());
                        return productoConsulta;
                    }));
        });
    }
}
