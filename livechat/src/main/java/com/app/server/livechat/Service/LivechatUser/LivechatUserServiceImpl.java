package com.app.server.livechat.Service.LivechatUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.server.livechat.DTO.LivechatUserDTO;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Repository.User.UserRepository;
import com.app.server.livechat.Utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

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
                claims.put("id", userCredentials.get().getId().toString());
                
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

  
    
    @Override
    public boolean validateUser(String token) {
    
        try{
            Jws<Claims> jwtClaims =jwtUtils.validateToken(token);
            Claims claims = jwtClaims.getBody();
            String authToken = claims.get("token").toString();

            if (claims != null && authToken.length()>0)  {
                return true;
                
            }
            
        }
        catch (Exception e) {
            // Token invalid
            return false;
        }
        return false;

    }

}
