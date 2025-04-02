package com.app.server.livechat.Controller.LivechatUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.server.livechat.DTO.LivechatUserDTO;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Entity.User.User;
import com.app.server.livechat.Repository.User.UserRepository;
import com.app.server.livechat.Service.CookiesService;
import com.app.server.livechat.Service.LivechatUser.LivechatUserServiceImpl;
import com.app.server.livechat.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    LivechatUserServiceImpl livechatUserService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CookiesService cookiesService;

    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody User userFormCredentials, HttpServletResponse  response) {
        try {
            LivechatUserDTO responseDTO = livechatUserService.login(userFormCredentials.getEmail(), userFormCredentials.getPassword());

            if(responseDTO != null){             
                cookiesService.addCookie("auth-cookie", responseDTO.getToken(), response);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
