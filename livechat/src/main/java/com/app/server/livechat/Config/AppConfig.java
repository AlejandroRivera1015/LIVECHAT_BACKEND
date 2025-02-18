package com.app.server.livechat.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.server.livechat.Config.Security.JwtAuthValidatorFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {


    @Autowired
    private JwtAuthValidatorFilter jwtAuthValidatorFilter;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        return http.csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
                
    }

}
