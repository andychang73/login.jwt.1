package com.abstractionizer.login.jwt.utils;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class Util {

    public String md5(String s){

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = digest.digest();

            for(Byte b : bytes){
                sb.append(hexDigits[(b >> 4) & 0x0f]);
                sb.append(hexDigits[b & 0x0f]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
