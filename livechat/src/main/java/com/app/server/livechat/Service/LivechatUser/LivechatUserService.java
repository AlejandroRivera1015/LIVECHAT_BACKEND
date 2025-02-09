package com.app.server.livechat.Service.LivechatUser;

import org.springframework.stereotype.Service;

import com.app.server.livechat.DTO.LivechatUserDTO;


@Service
public interface LivechatUserService {

    String login(String email, String password);

}
