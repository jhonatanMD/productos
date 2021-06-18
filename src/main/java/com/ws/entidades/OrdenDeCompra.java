package com.ws.entidades;


import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ws.entidades.dto.ProductoDto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collation = "OrdenDeCompra")
public class OrdenDeCompra {

	
	
	private List<ProductoDto> productos;
}
