package com.app.server.livechat.Service.LivechatUser;
import java.util.HashMap;
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
    public LivechatUserDTO login(String email, String password){
        try {
            Optional<LivechatUser> userCredentials = userRepository.findByEmailAndPassword(email, password);
            if (userCredentials.isPresent()) {
                Map<String, String> claims = new HashMap<>();
                claims.put("Role", userCredentials.get().getRole());
                claims.put("id", userCredentials.get().getId().toString());
                String token = jwtUtils.generateToken(claims);
                System.out.println("token generado" + token);
                Long userId = userCredentials.get().getId();
                return new LivechatUserDTO(userId, token);
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
    public Long getUserId(String email, String password) {
        try {
            Optional<LivechatUser> userCredentials = userRepository.findByEmailAndPassword(email, password);
            if (userCredentials.isPresent()) {
                return userCredentials.get().getId();
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
    public boolean setWsId(Long userId,String wsId){ 
        try{
            int user = userRepository.setWsId(userId, wsId);
            System.out.println("setWsId: " + wsId);

        }
        catch (Exception e) {
            System.out.println("Error setting wsId /" + e);
            // Token invalid
            return false;
        }
        return false;
    }

    public String getWsId(Long userId){
        try{
            return userRepository.findWsIdById(userId);
        }catch (Exception e) {
            return null;
    }
}
}
