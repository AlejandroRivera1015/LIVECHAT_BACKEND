package com.app.server.livechat.Service.LivechatUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.server.livechat.DTO.LivechatUserDTO;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Entity.User.User;
import com.app.server.livechat.Repository.User.UserRepository;
import com.app.server.livechat.Utils.JwtUtils;

@Service
public class LivechatUserServiceImpl implements LivechatUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;



    @Override
    public String login(String email, String password){


        try {
            Optional<LivechatUser> userCredentials = userRepository.findByEmailAndPassword(email, password);
            if (userCredentials.isPresent()) {

                Map<String, String> claims = new HashMap<>();
                claims.put("Role", userCredentials.get().getRole());
                claims.put("UserName", userCredentials.get().getUserName());

                return jwtUtils.generateToken(claims);

        

                
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("error finding user /" + e);
        }

        return null;
    }
}
