package com.app.server.livechat.Entity.User;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class User {

    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
