package com.app.server.livechat.Service.Messages;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.server.livechat.Entity.Conversation.Conversation;
import com.app.server.livechat.Entity.Message.Message;

@Service
public interface MessagesServices {

    public List<Message> handleMessage(Long conversationId, String message, Long senderId, Long receiverId);

}

