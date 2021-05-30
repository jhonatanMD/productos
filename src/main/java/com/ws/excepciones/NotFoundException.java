package com.ws.excepciones;


/**
 * NotFoundException clase para controlar exceptiones de NOT FOUNT
 * @version 1.0, 22/04/2021
 * @author Jhonatan Mallqui
 */
public class NotFoundException extends RuntimeException{

    private static final String DESCRIPTION = "NotFound Exception (400)";

    public NotFoundException(String detail){
        super(DESCRIPTION + " . "+detail);
    }
}
