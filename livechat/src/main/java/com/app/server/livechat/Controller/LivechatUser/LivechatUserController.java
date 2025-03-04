package com.app.server.livechat.Controller.LivechatUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.server.livechat.DTO.LivechatUserDTO;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Entity.User.User;
import com.app.server.livechat.Repository.User.UserRepository;
import com.app.server.livechat.Service.CookiesService;
import com.app.server.livechat.Service.LivechatUser.LivechatUserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.http.HttpHeaders;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/auth")
public class LivechatUserController {

    @Autowired
    LivechatUserServiceImpl livechatUserService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CookiesService cookiesService;

    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody User userFormCredentials, HttpServletResponse  response) {
        try {


            String responseToken = livechatUserService.login(userFormCredentials.getEmail(), userFormCredentials.getPassword());
            
            if(responseToken != null){
                cookiesService.addCookie("auth-cookie", responseToken, response);
                    return new ResponseEntity<>(new LivechatUserDTO(responseToken), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }
         
   
        } catch (Exception e) {
            // TODO: handle exception
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    
}
