package com.ws.web;

import com.ws.entidades.OrdenDeCompra;
import com.ws.servicios.impl.OrdenDeCompraServiceImpl;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public Single<OrdenDeCompra> guardar(@RequestBody OrdenDeCompra ordenDeCompra) {
	 	return service.guardarOrdenCompra(ordenDeCompra);

	}


	@GetMapping("/buscarPorId/{id}")
	public Maybe<OrdenDeCompra> buscarPorId(@PathVariable("id") String id){
		
		return service.buscarPorId(id);
		
	}
	
}
