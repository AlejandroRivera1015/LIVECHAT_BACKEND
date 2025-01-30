package com.app.server.livechat.Service.LivechatUser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.server.livechat.DTO.LivechatUserDTO;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Entity.User.User;
import com.app.server.livechat.Repository.User.UserRepository;

@Service
public class LivechatUserServiceImpl implements LivechatUserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public LivechatUserDTO login(String email, String password){


        try {
            Optional<LivechatUser> userCredentials = userRepository.findByEmailAndPassword(email, password);
            if (userCredentials.isPresent()) {
                return new LivechatUserDTO(userCredentials.get().getId(), "tokeeee");
                
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
