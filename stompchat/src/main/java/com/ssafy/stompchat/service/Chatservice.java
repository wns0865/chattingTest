package com.ssafy.stompchat.service;

import com.ssafy.stompchat.dto.ChatRoom;
import com.ssafy.stompchat.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.CSS;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class Chatservice {
    private final ChatRoomRepository chatRoomRepository;
   public Chatservice(ChatRoomRepository chatRoomRepository){
       this.chatRoomRepository=chatRoomRepository;
   }
    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId).orElse(null);
    }

    public ChatRoom createChatRoom(String roomName) {
        ChatRoom chatRoom = ChatRoom.create(roomName);
        return chatRoomRepository.save(chatRoom);
    }

    public void plusUserCnt(String roomId) {
        ChatRoom room = findRoomById(roomId);
        room.setUserCount(room.getUserCount() + 1);
        chatRoomRepository.save(room);
    }

    public void minusUserCnt(String roomId) {
        ChatRoom room = findRoomById(roomId);
        room.setUserCount(room.getUserCount() - 1);
        chatRoomRepository.save(room);
    }

    public String addUser(String roomId, String userName) {
        ChatRoom room = findRoomById(roomId);
        String userUUID = UUID.randomUUID().toString();
        room.getUserList().put(userUUID, userName);
        chatRoomRepository.save(room);
        return userUUID;
    }

    public void delUser(String roomId, String userUUID) {
        ChatRoom room = findRoomById(roomId);
        room.getUserList().remove(userUUID);
        chatRoomRepository.save(room);
    }

    public String getUserName(String roomId, String userUUID) {
        ChatRoom room = findRoomById(roomId);
        return room.getUserList().get(userUUID);
    }

    public List<String> getUserList(String roomId) {
        ChatRoom room = findRoomById(roomId);
        return new ArrayList<>(room.getUserList().values());
    }
}
