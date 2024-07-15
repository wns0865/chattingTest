package com.ssafy.stompchat.repository;

import com.ssafy.stompchat.dto.Chat;
import com.ssafy.stompchat.dto.ChatMessage;
import com.ssafy.stompchat.dto.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String> {

    // roomId로 채팅 메시지를 찾는 메서드
    List<ChatMessage> findByRoomId(String roomId);


   }
