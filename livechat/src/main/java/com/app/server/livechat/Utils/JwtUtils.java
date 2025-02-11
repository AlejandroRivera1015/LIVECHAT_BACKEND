package com.app.server.livechat.Utils;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(key,SignatureAlgorithm.HS256)
            .compact();
    }

    public void validateToken(String token){
        
        try {
            Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws (token);
                        System.out.println("ok validating Token");

        } catch (Exception e) {
            System.out.println("error validating Token");
        }
        

    }




}
