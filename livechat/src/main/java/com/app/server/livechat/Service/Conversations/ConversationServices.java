package com.app.server.livechat.Service.Conversations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.server.livechat.Entity.Conversation.Conversation;

@Service
public interface ConversationServices {

    public Long matchUsersConversations(Long user1, Long user2);
    public List<Conversation> getAllUserConversations(Long userId);

}
