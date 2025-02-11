package com.app.server.livechat.Controller.LivechatUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.server.livechat.DTO.LivechatUserDTO;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Entity.User.User;
import com.app.server.livechat.Repository.User.UserRepository;
import com.app.server.livechat.Service.LivechatUser.LivechatUserServiceImpl;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class LivechatUserController {

    @Autowired
    LivechatUserServiceImpl livechatUserService;

    @Autowired
    UserRepository userRepository;




    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody User userFormCredentials) {
        try {
            String response = livechatUserService.login(userFormCredentials.getEmail(), userFormCredentials.getPassword());
            System.out.println("resp"+response);
            return new ResponseEntity<>(new LivechatUserDTO(response), HttpStatus.OK);

        
   
        } catch (Exception e) {
            // TODO: handle exception
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    
}
