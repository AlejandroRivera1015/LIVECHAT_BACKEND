package com.app.server.livechat.Utils;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

@Component
@Getter
public class JwtUtils {

    private Date expirationDate;
    
    @Value("${livechat.security.jwt}")
    private String secret ;

    public String generateToken(Map<String,String> tokenClaims ){

        byte[] decodedKey = Base64.getDecoder().decode(getSecret());
        SecretKey key = Keys.hmacShaKeyFor(decodedKey);
        return Jwts.builder()
            .setClaims(tokenClaims)
            .setSubject(tokenClaims.get("id"))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(key,SignatureAlgorithm.HS256)
            .compact();
    }

   
   
    public Jws<Claims>  validateToken(String token){

        
        try {
            return Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws(token);

        } catch (Exception e) {
            System.out.println("JWTUTILS: "+e.getMessage());
        }

        return null;
    }


    public Long getIdByClaims(String authToken){

        String token = authToken.substring(7);
        Jws<Claims> userClaimsJws = this.validateToken(token);
        Claims userClaims = userClaimsJws.getBody();


        if(!userClaims.isEmpty()){
            return  Long.parseLong( userClaims.get("id").toString());
        }

        return null;

    
    }



}
