package com.app.server.livechat.Controller.LivechatUser;

import com.app.server.livechat.Entity.Conversation.Conversation;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Repository.Conversations.ConversationRepository;
import com.app.server.livechat.Repository.User.UserRepository;
import com.app.server.livechat.Service.LivechatUser.LivechatUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class LivechatUserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConversationRepository conversationRepository;

    @GetMapping("find")
    public Long findUser(@RequestParam(required = false) String email, @RequestParam(required = false) Long conversationId) {
        System.out.println("email: " + email);


        try{
            if(!email.isEmpty()){
                LivechatUser user =  userRepository.findByEmail(email).get();
                if(!user.getUserName().isEmpty()){
                    return user.getId();
                }
            }
            else{
                if(conversationId != null){
                    Conversation conversation = conversationRepository.findById(conversationId).get();
                    conversation.getParticipants();
                }

            }

        }catch (Exception e){
                return null;
            }
        return null;
        }

    @GetMapping("getUserName")
    public ResponseEntity<?> getUsername(@RequestParam Long userId){
        try{
            String userName = userRepository.findById(userId).get().getUserName();
            if(userName != null && !userName.isEmpty()){
                return new ResponseEntity<>(userName, HttpStatus.OK);
            }
            return  new ResponseEntity<>("user", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return  new ResponseEntity<>("user", HttpStatus.NOT_FOUND);
        }

        }
    }

