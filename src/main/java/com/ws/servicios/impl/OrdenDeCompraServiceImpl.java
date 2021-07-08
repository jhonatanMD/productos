package com.ws.servicios.impl;

import com.ws.util.Constantes;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.entidades.OrdenDeCompra;
import com.ws.repositorio.OrdenDeCompraRepositorio;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class OrdenDeCompraServiceImpl  {

	
	@Autowired
	private OrdenDeCompraRepositorio repositorio;
	
	
	public Flowable<OrdenDeCompra> obtenerOrdenDeCompra(int sede) {
		
		return repositorio.findByIdSede(sede);
	}
	
	public Single<OrdenDeCompra> guardarOrdenCompra(OrdenDeCompra ordenDeCompra){

		return Observable.fromIterable(ordenDeCompra.getProductos()).map(producto -> {
			producto.setPrecioTotal(producto.getPrecio().multiply(new BigDecimal(producto.getCantidad())));
			return producto;
		}).toList().map(productos -> {
			double total = productos.stream().parallel().mapToDouble(d -> d.getPrecioTotal().doubleValue()).sum();
			ordenDeCompra.setIgv(new BigDecimal(total * Constantes.IGV/100).setScale(2, RoundingMode.FLOOR));
			ordenDeCompra.setSubTotal(new BigDecimal(total).subtract(ordenDeCompra.getIgv()).setScale(2, RoundingMode.FLOOR));
			ordenDeCompra.setProductos(productos);
			ordenDeCompra.setTotalPagar(ordenDeCompra.getSubTotal().add(ordenDeCompra.getIgv()).setScale(2, RoundingMode.FLOOR));
			return productos;
		}).flatMap(r ->	 repositorio.save(ordenDeCompra));
	}


	public Maybe<OrdenDeCompra> buscarPorId(String id){
		return repositorio.findById(id);
	}
	
	
	
}
