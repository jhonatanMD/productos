package com.ws.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.entidades.OrdenDeCompra;
import com.ws.repositorio.OrdenDeCompraRepositorio;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Service
public class OrdenDeCompraServiceImpl  {

	
	@Autowired
	private OrdenDeCompraRepositorio repositorio;
	
	
	public Flowable<OrdenDeCompra> obtenerOrdenDeCompra(int sede) {
		
		return repositorio.findByIdSede(sede);
	}
	
	public Single<OrdenDeCompra> guardarOrdenCompra(OrdenDeCompra ordenDeCompra){
		
		return repositorio.save(ordenDeCompra);
	}


	public Maybe<OrdenDeCompra> buscarPorId(String id){
		
		return repositorio.findById(id);
	}
	
	
	
}
