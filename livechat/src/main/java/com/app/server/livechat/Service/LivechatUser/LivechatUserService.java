package com.app.server.livechat.Service.LivechatUser;

import org.springframework.stereotype.Service;

import com.app.server.livechat.DTO.LivechatUserDTO;


@Service
public interface LivechatUserService {

    LivechatUserDTO login(String email, String password);
    boolean setWsId(Long userId, String wsId);
    Long getUserId(String email, String password);

}
