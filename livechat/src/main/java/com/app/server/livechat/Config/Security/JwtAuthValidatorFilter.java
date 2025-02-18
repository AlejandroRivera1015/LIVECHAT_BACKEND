package com.app.server.livechat.Config.Security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String header = request.getHeader("Authorization");
        String jwtToken = header.substring(7);
        System.out.println("token:"+jwtToken);

        //TODO : validate the token
        if(jwtToken != null && jwtToken.length() > 0){

        
        }
        filterChain.doFilter(request, response);


    }
}