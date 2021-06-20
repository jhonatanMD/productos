package com.ws.repositorio;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

import com.ws.entidades.OrdenDeCompra;

import io.reactivex.Flowable;

public interface OrdenDeCompraRepositorio extends RxJava2CrudRepository<OrdenDeCompra, String>{

	 @Query(value = "{'idSede':?0}")
	Flowable<OrdenDeCompra> findByIdSede(int idSede);
}
