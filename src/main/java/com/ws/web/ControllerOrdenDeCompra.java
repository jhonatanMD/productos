package com.ws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.entidades.OrdenDeCompra;
import com.ws.servicios.impl.OrdenDeCompraServiceImpl;

import io.reactivex.Flowable;
import io.reactivex.Single;

@RestController
@RequestMapping("/api/ordenCompra")
@CrossOrigin("*")
public class ControllerOrdenDeCompra {

	
	@Autowired
	private OrdenDeCompraServiceImpl service;
	
	@GetMapping("/buscar/{sede}")
	public Flowable<OrdenDeCompra> buscar(@PathVariable("sede") int sede){
		
		return service.obtenerOrdenDeCompra(sede);
		
	}

	@PostMapping("/guardar")
	public Single<OrdenDeCompra> guardar(@RequestBody OrdenDeCompra ordenDeCompra){
		
		return service.guardarOrdenCompra(ordenDeCompra);
	}
	
}
