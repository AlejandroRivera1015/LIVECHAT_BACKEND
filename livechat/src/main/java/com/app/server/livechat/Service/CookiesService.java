package com.app.server.livechat.Service;

import java.net.http.HttpHeaders;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookiesService {

    public void addCookie(String name, String value, HttpServletResponse response){
        Cookie authCookie = new Cookie(name, value);
        authCookie.setHttpOnly(true);
        authCookie.setAttribute("SameSite", "Lax"); 
        authCookie.setSecure(false);
        authCookie.setPath("/");
        authCookie.setMaxAge(60*60*24*7);
        response.addCookie(authCookie);
    }
}
