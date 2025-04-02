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
     /*    String requestId = UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Ejecutando getUserAllConversations [" + requestId + "] - userId: " + userId 
                        + " - Thread: " + Thread.currentThread().getId()
                        + " - Path: " + request.getRequestURI() 
                        + " - QueryString: " + request.getQueryString());
        */
        List<Conversation> conversations = conversationServices.getAllUserConversations(userId);
        return new ResponseEntity<>(conversations, HttpStatus.OK);
        
    }
}
