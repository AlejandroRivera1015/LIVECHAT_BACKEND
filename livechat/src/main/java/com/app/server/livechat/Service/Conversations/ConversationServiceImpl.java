package com.app.server.livechat.Service.Conversations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.server.livechat.Entity.Conversation.Conversation;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.app.server.livechat.Repository.Conversations.ConversationRepository;
import com.app.server.livechat.Repository.User.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ConversationServiceImpl implements ConversationServices {

    @Autowired
    UserRepository userRepository;

    @Autowired 
    ConversationRepository  conversationRepository;

    @Override
    @Transactional
    public Long matchUsersConversations(Long userId, Long contactId) {
        try {
            if(conversationRepository.findConversationsByParticipants(userId, contactId)  == null){
                System.out.println("no hay conversaciones relacionadas");
                System.out.println("user 1" + userRepository.findById(contactId));

                Conversation newConversation = new Conversation();

                LivechatUser user = userRepository.findById(userId).get();
                LivechatUser contact = userRepository.findById(contactId).get();

                List<LivechatUser> participants = new ArrayList<>();
                participants.add(user);
                participants.add(contact);

                newConversation.setParticipants(participants);

                user.getConversations().add(newConversation);
                contact.getConversations().add(newConversation);


                System.out.println("Conversacion creada: " + newConversation.toString());
                
                conversationRepository.save(newConversation);

    
                return newConversation.getId();
            }
            
            else {
                return conversationRepository.findConversationsByParticipants(userId, contactId).getId();
            }

        } 
        catch (Exception e) {
            System.out.println("Error al buscar conversaciones de contacotos: " + e);
            return null;
        }
        finally {
            System.out.println("Fin de la busqueda de conversaciones");
        }
    }


    @Override
    public List<Conversation> getAllUserConversations(Long userId) {
        return conversationRepository.findAllUserConversations(userId);
    }

}
