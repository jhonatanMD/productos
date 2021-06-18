package com.ws.web;

import com.ws.entidades.ConsultasVoz;
import com.ws.entidades.dto.ProductoConsulta;
import com.ws.entidades.dto.RespuestaVoz;
import com.ws.servicios.impl.ConsultaVozServiceImpl;
import com.ws.util.Constantes;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/consulta")
@CrossOrigin("*")
public class ControllerConsultaVoz {


    @Autowired
    private ConsultaVozServiceImpl consultaVozService;

    @PostMapping("/consultar_voz")
    Maybe<?> consulta(HttpServletRequest req, @RequestBody @Valid ConsultasVoz.consulta request) {

        String consulta = request.getConsulta().toLowerCase();

        if(consulta.indexOf(Constantes.CONSULTA_PRODUCTO_1) == 0) {

            String producto = consulta.replace(Constantes.CONSULTA_PRODUCTO_1, "").trim();
            return consultaVozService.productos(producto,req)
                    .map(res -> new ProductoConsulta().new RespuestaConsultaProducto("Se encontro lo siguiente de "+producto,res))
                    .switchIfEmpty(Maybe.just(new ProductoConsulta().new RespuestaConsultaProducto("No se encontro producto "+producto, Arrays.asList())));
        }else if (consulta.indexOf(Constantes.CONSULTA_PRODUCTO_2) == 0){
            String producto =consulta.replace(Constantes.CONSULTA_PRODUCTO_2,"").trim();
            return consultaVozService.productos(producto,req).map(res -> new ProductoConsulta().new RespuestaConsultaProducto("Se encontro lo siguiente de "+producto,res))
                    .switchIfEmpty(Maybe.just(new ProductoConsulta().new RespuestaConsultaProducto("No se encontro producto "+producto, Arrays.asList())));
        }else if (consulta.indexOf(Constantes.CONSULTA_PRODUCTO_4) == 0){
            String producto =consulta.replace(Constantes.CONSULTA_PRODUCTO_4,"").trim();
            return consultaVozService.productos(producto,req).map(res -> new ProductoConsulta().new RespuestaConsultaProducto("Se encontro lo siguiente de "+producto,res))
                    .switchIfEmpty(Maybe.just(new ProductoConsulta().new RespuestaConsultaProducto("No se encontro producto "+producto, Arrays.asList())));
        }else if (consulta.indexOf(Constantes.CONSULTA_PRODUCTO_3) == 0){
            String producto =consulta.replace(Constantes.CONSULTA_PRODUCTO_3,"").trim();
            return consultaVozService.productos(producto,req).map(res -> new ProductoConsulta().new RespuestaConsultaProducto("Se encontro lo siguiente de "+producto,res))
                    .switchIfEmpty(Maybe.just(new ProductoConsulta().new RespuestaConsultaProducto("No se encontro producto "+producto, Arrays.asList())));
        }

        return Maybe.just(new RespuestaVoz("No te entiendo"));
    }

}
