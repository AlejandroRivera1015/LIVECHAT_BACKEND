package com.app.server.livechat.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestDTO {

    private Long id;
    private String message;
    private List<Long> participants;
    private String date;

    public MessageRequestDTO() {}
    public MessageRequestDTO(String message, List<Long> participants) {
        this.message = message;
        this.participants = participants;
    }
    public MessageRequestDTO(String message, List<Long> participants, String date){
        this.message = message;
        this.participants = participants;
        this.date = date;
    }




}
