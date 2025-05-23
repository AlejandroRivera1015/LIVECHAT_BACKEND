package com.app.server.livechat.Entity.Conversation;

import java.util.List;

import com.app.server.livechat.Entity.Message.Message;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "conversations")
    @JsonBackReference
    private List<LivechatUser> participants;

    @OneToMany(mappedBy = "conversation")  
    @JsonManagedReference
    private List<Message> conversationMessages;


    @Override
    public String toString(){
            return "Conversation [conversationId=" + this.id + "]";
    }

    public Conversation(List<LivechatUser> partiLivechatUsers){
        this.participants = partiLivechatUsers;
    }







}
