package com.ssafy.stompchat.Chatting.repository;

import com.ssafy.stompchat.Chatting.dto.ChatMessage;
import com.ssafy.stompchat.Chatting.dto.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String> {

    // roomId로 채팅 메시지를 찾는 메서드
    List<ChatMessage> findByRoomId(String roomId);


   }
