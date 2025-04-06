package com.app.server.livechat.Entity.Message;

import org.checkerframework.checker.units.qual.g;

import com.app.server.livechat.Entity.Conversation.Conversation;
import com.app.server.livechat.Entity.User.LivechatUser;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    @JsonBackReference
    private Conversation conversation;
    private String message;
    private Long sender;
    private Long receiver;
    private String createAt;

    @Override
    public String toString() {
        return "Message [id=" + id + ", conversation=" + conversation + ", message=" + message + ", sender=" + sender
                + ", receiver=" + receiver + ", createAt=" + createAt + "]";
    }


    public Message(Conversation conversation, String message, Long sender, Long receiver, String createAt) {
        this.conversation = conversation;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.createAt = createAt;
    }




}
