package com.ws.repositorio;

import com.ws.entidades.ConsultasVoz;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface ConsultaVozRepositorio extends RxJava2CrudRepository<ConsultasVoz,Long> {
}
