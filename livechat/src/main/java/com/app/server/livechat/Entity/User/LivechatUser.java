package com.app.server.livechat.Entity.User;

import java.util.List;

import com.app.server.livechat.Entity.Conversation.Conversation;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
public class LivechatUser extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "livechatUsers_conversations", joinColumns =  @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns =  @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    )
    private List<Conversation> conversations;
    
    private String wsId;
    


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

    public String  toString(){
        return "LivechatUser [id=" + id + ", userName=" + userName + "]";
    }


}
