package com.app.server.livechat.Repository.Conversations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
import org.springframework.stereotype.Repository;

import com.app.server.livechat.Entity.Conversation.Conversation;


@Repository
public interface ConversationRepository extends  JpaRepository<Conversation,Long> {


    @Query("SELECT c FROM Conversation c JOIN c.participants p WHERE p.id = :userId")
    public List<Conversation> findAllUserConversations(@Param("userId") Long userId);



    @Query("SELECT c FROM Conversation c JOIN c.participants u1 JOIN c.participants u2 WHERE u1.id = :userId AND u2.id = :contactId ")
    public Conversation findConversationsByParticipants(@Param("userId") Long userId, @Param("contactId") Long contactId);


    
}
