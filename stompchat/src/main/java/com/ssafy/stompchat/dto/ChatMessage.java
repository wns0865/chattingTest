package com.ssafy.stompchat.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "sender")
    private String sender;

    @Column(name = "message")
    private String message;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    private Chat.MessageType type;


    // Chat DTO로부터 ChatMessage 엔티티를 생성하는 정적 메서드
    public static ChatMessage fromChat(Chat chat) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(chat.getRoomId());
        chatMessage.setSender(chat.getSender());
        chatMessage.setMessage(chat.getMessage());
        chatMessage.setType(chat.getType());
        chatMessage.setSentAt(LocalDateTime.now());
        return chatMessage;
    }
}