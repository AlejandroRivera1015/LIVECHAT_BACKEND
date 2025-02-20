package com.app.server.livechat.Config.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.server.livechat.Utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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
            Jws<Claims> claims = jwtUtils.validateToken(jwtToken);

            if(claims != null){
                System.out.println("los claims del filter" +claims.getBody());
                Authentication auth = createAuthentication(claims.getBody());
                System.out.println("auth es" +auth);

                SecurityContextHolder.getContext().setAuthentication(auth);
                


            }

        
        }
        filterChain.doFilter(request, response);


    }

    private Authentication createAuthentication(Claims claims) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(claims.get("Role").toString()));

        UserDetails userDetails = new User(claims.getSubject(), "", new ArrayList<>());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    
}