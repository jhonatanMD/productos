package com.ws.excepciones;


/**
 * NotFoundException clase para controlar exceptiones de NOT FOUNT
 * @version 1.0, 22/04/2021
 * @author Jhonatan Mallqui
 */
public class ProductosNoExistentesException extends RuntimeException{

    private static final String DESCRIPTION = "Productos (200)";

    public ProductosNoExistentesException(String detail){
        super(DESCRIPTION + " . "+detail);
    }
}
