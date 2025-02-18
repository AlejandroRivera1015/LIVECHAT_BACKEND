package com.app.server.livechat.Config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.server.livechat.Utils.JwtUtils;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthValidatorFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String header = request.getHeader("Authorization");
        String jwtToken = header.substring(7);
        System.out.println("token:"+jwtToken);

        //TODO : validate the token
        if(jwtToken != null && jwtToken.length() > 0){
            jwtUtils.validateToken(jwtToken);

        
        }
        filterChain.doFilter(request, response);


    }
}