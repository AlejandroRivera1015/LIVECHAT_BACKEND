package com.app.server.livechat.Entity.User;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
@Getter
@MappedSuperclass
@ToString
public class User {

    private String email;
    private String password;
    private String role;

    public User() {
        
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setRole(String role){
        this.role = role;
    }

}
