package com.app.server.livechat.Entity.Conversation;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import com.app.server.livechat.Entity.Message.Message;
import com.app.server.livechat.Entity.User.LivechatUser;

@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "conversation_participants",
    joinColumns = @JoinColumn(name = "conversation_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<LivechatUser> participants;

    @OneToMany(mappedBy = "conversation")
    private List<Message> conversationMessages;



    public Conversation(List<LivechatUser> partiLivechatUsers){
        this.participants = partiLivechatUsers;
    }






}
