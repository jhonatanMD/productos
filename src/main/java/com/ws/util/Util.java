package com.ws.util;

import org.apache.commons.codec.binary.Base64;

public class Util {



    public static String encript(String password) {
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(password.getBytes()));
    }

    public static String decrypt(String password) {
        return new String(Base64.decodeBase64(password.getBytes()));
    }

    public static int cambiarEstado(int estado){
        if(estado == 0)
            return 1;
        return 0;
    }
}
