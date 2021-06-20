package com.ws.entidades;


import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


import com.ws.entidades.dto.ProductoDto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document
public class OrdenDeCompra {

	@Id
	private String id;
	private String razonSocial;
	private String rucEmpresa;
	private String direccionEmpresa;
	private String numeroSerie;
	private String sresEmpresa;
	private String rucProveedor;
	private String direccionProveedor;
	private String fecha;
	private String emailProveedor;
	private String telefono1Proveedor;
	private String atencionProveedor;
	private String telefono2Proveedor;
	private String formatoPago;
	private String cotizacionProveedor;
	private String requerimientosProveedor;
	private String unidadProveedor;
	private String motivoProveedor;
	private String plazoEntrega;
	private String tipoCuenta;
	private String numeroCuenta;
	private List<ProductoDto> productos;
	private BigDecimal subTotal;
	private BigDecimal igv;
	private BigDecimal totalPagar;
	private String totalTexto;
	private String observacion;
	private int codEstado;
	private int idSede;
}
