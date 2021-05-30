package com.ws.excepciones;

import lombok.Getter;

/**
 * ResponseErrorException clase para respuesta de exceptiones
 * @version 1.0, 22/04/2021
 * @author Jhonatan Mallqui
 */
@Getter
public class ResponseErrorException {

    private String exception;
    private String message;
    private String path;

    public ResponseErrorException(String exception,String message, String path) {
        this.exception = exception;
        this.message = message;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ResponseException{" +
                "exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
