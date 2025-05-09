package com.app.server.livechat.DTO;

import com.app.server.livechat.Entity.Message.Message;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class ConversationResponseDTO {
    private Long id;
    private List<Message> conversationMessages;
}
