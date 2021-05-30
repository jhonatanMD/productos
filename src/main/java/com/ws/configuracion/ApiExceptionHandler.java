package com.ws.configuracion;

import com.ws.excepciones.BadRequestException;
import com.ws.excepciones.NotFoundException;
import com.ws.excepciones.ResponseErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ApiExceptionHandler clase de control de errores
 * @version 1.0, 22/04/2021
 * @author Jhonatan Mallqui
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /*@ExceptionHandler({
            BadRequestException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class

    })*/
    @ResponseBody
    public ResponseErrorException badResquest(HttpServletRequest request , Exception exception){
        return new ResponseErrorException("BAD REQUEST",exception.getMessage(), request.getRequestURI());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }
/*
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({
    })
    @ResponseBody
    public ResponseErrorException notAcceptable(HttpServletRequest request , Exception exception){

        return new ResponseErrorException("NOT ACCEPTABLE","Dato ingresado no aceptado", request.getRequestURI());
    }
*/


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NoSuchElementException.class
    })
    @ResponseBody
    public ResponseErrorException exception(HttpServletRequest  request , Exception exception){

        return new ResponseErrorException("Not Found","No se encontro entidad", request.getRequestURI());
    }


}
