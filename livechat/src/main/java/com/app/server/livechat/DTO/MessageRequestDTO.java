package com.app.server.livechat.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestDTO {

    private Long id;
    private String message;
    private Long sender;
    private Long receiver;

    public MessageRequestDTO() {}
    public MessageRequestDTO(String message, Long sender, Long receiver){ 
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }





}
