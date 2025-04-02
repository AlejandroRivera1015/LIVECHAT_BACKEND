package com.app.server.livechat.Repository.Messages;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.server.livechat.Entity.Message.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByConversationId(Long conversationId);
    


}
