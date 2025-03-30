package com.app.server.livechat.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactLivechatDTO {

    private String userName;
    private Long userId;


    public ContactLivechatDTO(String userName, Long userId) {
        this.userName = userName;
        this.userId = userId;
    }

}
    