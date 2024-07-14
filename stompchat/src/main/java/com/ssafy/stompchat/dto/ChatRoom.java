package com.ssafy.stompchat.dto;

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
    private String roomName;
    private long userCount;
//    private HashMap<String ,String> userList = new HashMap<String,String>();

    @ElementCollection
    @CollectionTable(name = "chat_room_users", joinColumns = @JoinColumn(name = "room_id"))
    @MapKeyColumn(name = "user_id")
    @Column(name = "user_name")
    private Map<String, String> userList = new HashMap<>();
    public static ChatRoom create(String roomName){
        ChatRoom chatRoom= new ChatRoom();
        chatRoom.roomId= UUID.randomUUID().toString();
        chatRoom.roomName=roomName;

        return chatRoom;
    }
}
