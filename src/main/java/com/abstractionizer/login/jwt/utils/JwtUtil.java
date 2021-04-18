package com.abstractionizer.login.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String secretKey = "fjdiqpjfdapjfdmkadjaiopufj";
    public static final String prefix = "Bearer ";
    private final long validity = 1000 * 60 * 60 * 24 * 7;

    public boolean isTokenValid(String token, String username){
        return getClaimsFromToken(token, Claims::getSubject).equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return getClaimsFromToken(token, Claims::getExpiration).before(new Date());
    }

    public Date getTokenExpiration(String token){
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public String createToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims getAllFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(getAllFromToken(token));
    }
}
