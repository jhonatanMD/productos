package com.ws.servicios.impl;

import com.ws.entidades.*;
import com.ws.entidades.dto.ProductoConsulta;
import com.ws.repositorio.ConsultaVozRepositorio;
import com.ws.repositorio.ProductoRepositorio;
import com.ws.repositorio.TipoRepositorio;
import com.ws.servicios.IService;
import com.ws.util.ConvercionToken;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaVozServiceImpl {


    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private ConsultaVozRepositorio consultaVozRepositorio;

    @Autowired
    private IService<Almacen,String> almacenService;


    @Autowired
    private IService<Marca,Long> marcaService;

    @Autowired
    private TipoRepositorio tipoRepositorio;



    public Maybe<ConsultasVoz> busquedaPregunta(long id){

        return consultaVozRepositorio.findById(id);
    }


    public Maybe<List<ProductoConsulta>> productos(String nombre , HttpServletRequest req,Long idSede){

        return ConvercionToken.convercion(req).flatMapMaybe(token ->
             productoRepositorio.findByNombre(nombre)
                    .filter(producto -> producto.getId_sede() == idSede))
                    .flatMap(producto-> almacenService.findAlmacenByProducto(producto.getId())
                            .filter(almacen -> almacen.getId_sede() == idSede)
                            .flatMap(almacen -> {
                                ProductoConsulta productoConsulta = new ProductoConsulta();
                                productoConsulta.setColor(almacen.getColor());
                                productoConsulta.setGenero(almacen.getGenero());
                                productoConsulta.setDescripcion(almacen.getDescripcion());
                                productoConsulta.setStock(almacen.getStock());
                                productoConsulta.setMinimo_stock(almacen.getMinimo_stock());
                                productoConsulta.setPrecio(almacen.getPrecio());
                                productoConsulta.setTalla(almacen.getTalla());
                                productoConsulta.setProducto(producto.getNombre());
                                return marcaService.findById(almacen.getId_marca()).toFlowable().map(marca -> {
                                    productoConsulta.setMarca(marca.getMarca());
                                    return productoConsulta;
                                }).flatMapMaybe(r -> tipoRepositorio.findAllById(almacen.getId_tipo())
                                        .toList().toMaybe().map(tipos -> {
                                            productoConsulta.setTipos(tipos.stream()
                                                    .map(Tipo::getTipo).collect(Collectors.toList()));

                                            return productoConsulta;
                                        }));
                            }).toList().toMaybe());
    }



    public Maybe<List<ProductoConsulta>> productos_consulta(Long idSede) {

            return almacenService.findByIdSede(idSede).flatMapMaybe(almacen -> {
                return productoRepositorio.findById(almacen.getId_producto()).flatMap( producto -> {
                    ProductoConsulta productoConsulta = new ProductoConsulta();
                    productoConsulta.setColor(almacen.getColor());
                    productoConsulta.setGenero(almacen.getGenero());
                    productoConsulta.setDescripcion(almacen.getDescripcion());
                    productoConsulta.setStock(almacen.getStock());
                    productoConsulta.setMinimo_stock(almacen.getMinimo_stock());
                    productoConsulta.setPrecio(almacen.getPrecio());
                    productoConsulta.setTalla(almacen.getTalla());
                    productoConsulta.setProducto(producto.getNombre());
                   return  marcaService.findById(almacen.getId_marca()).map(marca -> {
                       productoConsulta.setMarca(marca.getMarca());
                       return productoConsulta;
                   }).flatMap(r -> tipoRepositorio.findAllById(almacen.getId_tipo())
                           .toList().toMaybe().map(tipos -> {
                               productoConsulta.setTipos(tipos.stream()
                                       .map(Tipo::getTipo).collect(Collectors.toList()));

                               return productoConsulta;
                           }));

                });
            }).toList().toMaybe();

    }
}
