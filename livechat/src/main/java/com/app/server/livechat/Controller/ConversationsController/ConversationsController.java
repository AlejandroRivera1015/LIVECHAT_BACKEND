package com.app.server.livechat.Controller.ConversationsController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.server.livechat.Entity.Conversation.Conversation;
import com.app.server.livechat.Service.Conversations.ConversationServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/conversations")
public class ConversationsController {
    @Autowired
    ConversationServiceImpl conversationServices;
    
    @GetMapping("/all")
    public ResponseEntity<?> getUserAllConversations(@RequestParam("userId") Long userId, HttpServletRequest request){
  
        List<Conversation> conversations = conversationServices.getAllUserConversations(userId);
        System.out.println("Conversations: " + conversations.toString());
        return new ResponseEntity<>(conversations, HttpStatus.OK);
        
    }

    @GetMapping("/match")
    public ResponseEntity<?> findConversation(@RequestParam("user") String userId, @RequestParam("contact") String contactId){
        try{
            System.out.println("Finding conversation between user: " + userId + " and contact: " + contactId);
            Long conversationId = conversationServices.matchUsersConversations(Long.parseLong(userId), Long.parseLong(contactId) );
            return new ResponseEntity<>(conversationId, HttpStatus.OK);

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
