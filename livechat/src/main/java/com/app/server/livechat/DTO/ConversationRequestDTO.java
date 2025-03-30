package com.app.server.livechat.DTO;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationRequestDTO {

    private String responseType;
    private ArrayList<Long> participants;

    public ConversationRequestDTO() {}

    public ConversationRequestDTO(String responseType, ArrayList<Long> participants) {
        this.responseType = responseType;
        this.participants = participants;
    }

}
