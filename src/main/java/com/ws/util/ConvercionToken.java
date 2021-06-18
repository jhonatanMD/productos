package com.ws.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.entidades.dto.Token;
import io.reactivex.Single;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

public class ConvercionToken {

    public static final Single<Token> convercion(HttpServletRequest req)  {

        Token datoToken = new Token();
        if(req != null) {
            String[] token = req.getHeader("Authorization").replace("Bearer", "").trim().split("\\.");
            ObjectMapper obj = new ObjectMapper();
            try {
                datoToken = obj.readValue(Util.decrypt(token[1]),Token.class);
            }catch (Exception e){

                System.out.println(e.getMessage());
            }


        }
        return Single.just(datoToken);

    }

}
