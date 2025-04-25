package com.app.server.livechat.DTO;

import lombok.Getter;
import lombok.Setter;

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



    public String getMessage() {
        return  this.message;
    }

    public Long getSender(){
        return  this.sender;
    }

    public Long getReceiver(){
        return this.receiver;
    }
}
