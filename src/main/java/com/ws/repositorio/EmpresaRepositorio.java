package com.ws.repositorio;

import com.ws.entidades.Empleado;
import com.ws.entidades.Empresa;
import com.ws.entidades.Producto;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface EmpresaRepositorio extends RxJava2CrudRepository<Empresa,Long> ,IRepositorios<Empresa> {
}
