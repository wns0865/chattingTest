package com.ssafy.stompchat.repository;

import com.ssafy.stompchat.dto.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String> {
}
