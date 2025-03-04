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

import com.app.server.livechat.Service.CookiesService;
import com.app.server.livechat.Utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthValidatorFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired 
    CookiesService cookiesService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        try {

            if(request.getRequestURI().contains("/auth/login")){
                System.out.println("la url es para auth" + request.getRequestURI());
                filterChain.doFilter(request, response);
            }

            else{

                for(Cookie cookie : request.getCookies()){

                    if(cookie.getName().equals("auth-cookie")){
                        String jwtToken = cookie.getValue();
                        System.out.println("jwt recibido de la cookie" + jwtToken);  
                        Jws<Claims> claims =  jwtUtils.validateToken(jwtToken);
                        Authentication auth = createAuthentication(claims.getBody());
                        SecurityContextHolder.getContext().setAuthentication(auth);


                    }
                    
                }
                filterChain.doFilter(request, response);


            }

        } catch (Exception e) {
            System.out.println("JwtAuthValidatorFilter: Error validating token" + e.getMessage());
        }

    }

    private Authentication createAuthentication(Claims claims) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(claims.get("Role").toString()));

        UserDetails userDetails = new User(claims.get("id").toString(), "", new ArrayList<>());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}