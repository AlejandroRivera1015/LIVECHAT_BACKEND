package com.app.server.livechat.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class LivechatUserDTO {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("token")
    private String token;

    public LivechatUserDTO(String token) {
        this.token = token;
    }

    public LivechatUserDTO(Long id, String token) {
        this.id = id;
        this.token = token;
    }

}
