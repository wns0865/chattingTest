package com.ssafy.stompchat.service;

import com.ssafy.stompchat.dto.Chat;
import com.ssafy.stompchat.dto.ChatMessage;
import com.ssafy.stompchat.dto.ChatRoom;
import com.ssafy.stompchat.repository.ChatRoomRepository;
import com.ssafy.stompchat.repository.ChatMessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.CSS;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class Chatservice {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
   public Chatservice(ChatRoomRepository chatRoomRepository, ChatMessageRepository chatMessageRepository){
       this.chatRoomRepository=chatRoomRepository;
       this.chatMessageRepository = chatMessageRepository;
   }

    public void saveMessage(Chat chat) {

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(chat.getRoomId());
        chatMessage.setSender(chat.getSender());
        chatMessage.setMessage(chat.getMessage());
        chatMessage.setType(chat.getType());
        chatMessage.setSentAt(LocalDateTime.now());
        chatMessageRepository.save(chatMessage);
    }
    public ChatRoom createChatRoom() {
        ChatRoom chatRoom = ChatRoom.create();
        return chatRoomRepository.save(chatRoom);
    }


    public void enterRoom(String roomId, String userUUID, String userName) {
        ChatRoom room = findRoomById(roomId);
        if (room != null) {
            room.setUserCount(room.getUserCount() + 1);
            room.getUserList().put(userUUID, userName);
            chatRoomRepository.save(room);
        }
    }

    public void leaveRoom(String roomId, String userUUID) {
        ChatRoom room = findRoomById(roomId);
        if (room != null) {
            room.setUserCount(room.getUserCount() - 1);
            room.getUserList().remove(userUUID);
            chatRoomRepository.save(room);
        }
    }

//    public void incrementUnreadCount(String roomId) {
//        ChatRoom room = findRoomById(roomId);
//        if (room != null) {
//            room.setUnreadMessageCount(room.getUnreadMessageCount() + 1);
//            chatRoomRepository.save(room);
//        }
//    }

    public void resetUnreadCount(String roomId) {
        ChatRoom room = findRoomById(roomId);
        if (room != null) {
            room.setUnreadMessageCount(0);
            chatRoomRepository.save(room);
        }
    }



    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId).orElse(null);
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
