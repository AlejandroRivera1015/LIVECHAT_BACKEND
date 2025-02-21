package com.app.server.livechat.Entity.User;

import java.util.List;

import com.app.server.livechat.Entity.Conversation.Conversation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

@Entity
@Getter
public class LivechatUser extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    



    @ManyToMany(mappedBy = "participants")
    private List<Conversation> conversations;
    


    public LivechatUser(){
        super();
    }


    public LivechatUser(String email, String password){
        super(email, password);
    }

    public LivechatUser(String email, String password, String userName){
        super(email, password);
        this.userName = userName;
    }





}
