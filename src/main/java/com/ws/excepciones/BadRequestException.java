package com.ws.excepciones;

/**
 * BadRequestException clase para controlar exceptiones de BAD REQUEST
 * @version 1.0, 22/04/2021
 * @author Jhonatan Mallqui
 */
public class BadRequestException extends RuntimeException{

    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail){
        super(DESCRIPTION + " . "+detail);
    }
}
