package com.ssafy.stompchat.Chatting.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@Entity
public class ChatRoom {
    @Id
    private String roomId;
    private long userCount;
    private int unreadMessageCount;
//    private HashMap<String ,String> userList = new HashMap<String,String>();

    @ElementCollection
    @CollectionTable(name = "chat_room_users", joinColumns = @JoinColumn(name = "room_id"))
    @MapKeyColumn(name = "user_id")
    @Column(name = "user_name")
    private Map<String, String> userList = new HashMap<>();
    public static ChatRoom create(){
        ChatRoom chatRoom= new ChatRoom();
        chatRoom.roomId= UUID.randomUUID().toString();
        chatRoom.userCount=0;
        chatRoom.unreadMessageCount=0;

        return chatRoom;
    }
}
