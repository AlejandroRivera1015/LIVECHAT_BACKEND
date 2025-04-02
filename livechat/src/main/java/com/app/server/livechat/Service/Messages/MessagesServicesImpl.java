package com.app.server.livechat.Service.Messages;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.server.livechat.Entity.Conversation.Conversation;
import com.app.server.livechat.Entity.Message.Message;
import com.app.server.livechat.Repository.Conversations.ConversationRepository;
import com.app.server.livechat.Repository.Messages.MessageRepository;

import jakarta.transaction.Transactional;


@Service
public class MessagesServicesImpl implements MessagesServices {
 

    @Autowired

    private ConversationRepository conversationRepository;

   
    @Autowired
    private MessageRepository messageRepository;
    
    
    @Override
    @Transactional
    public List<Message> handleMessage(Long conversationId, String message, Long senderId, Long receiverId) {
        System.out.println("manejando mensaje");
     

    try {
        Conversation conversation = conversationRepository.findById(conversationId).get();

        Date creationDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createAt = dateFormat.format(creationDate);


        if(messageRepository.findByConversationId(conversationId).size() == 0){
            List <Message> messages = new ArrayList<>();
                
                Message newMessage = new Message(conversation, message, conversation.getParticipants().get(0).getId(), conversation.getParticipants().get(1).getId(), createAt);
                messageRepository.save(newMessage);
                messages.add(newMessage);
                return messages;
    

        }
        else{
            System.out.println("hay mensajes relacionados");
            List <Message> conversationMessages = new ArrayList<>(messageRepository.findByConversationId(conversationId));
            Message newMessage = new Message(conversation, message, senderId, receiverId, createAt);
            conversationMessages.add(newMessage);
            messageRepository.save(newMessage);
            return conversationMessages;
        }
        
        
    } catch (Exception e) {
        System.out.println("Error al manejar el mensaje: " + e.getMessage());
        return new ArrayList<Message>();



    }


    }

}
