package com.ssafy.stompchat.repository;

import com.ssafy.stompchat.dto.Chat;
import com.ssafy.stompchat.dto.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    // ChatMessage 엔티티의 ID 타입이 Long이므로 JpaRepository<ChatMessage, Long>을 사용합니다.

    // roomId로 채팅 메시지를 찾는 메서드
    List<ChatMessage> findByRoomId(String roomId);

    // roomId로 채팅 메시지를 찾고 시간순으로 정렬하는 메서드
    List<ChatMessage> findByRoomIdOrderBySentAtAsc(String roomId);

    // 특정 발신자의 메시지를 찾는 메서드
    List<ChatMessage> findBySender(String sender);

    // roomId와 메시지 타입으로 채팅 메시지를 찾는 메서드
    List<ChatMessage> findByRoomIdAndType(String roomId, Chat.MessageType type);

    // 특정 기간 동안의 채팅 메시지를 찾는 메서드 (예: 최근 7일)
    @Query("SELECT cm FROM ChatMessage cm WHERE cm.roomId = :roomId AND cm.sentAt >= :startDate")
    List<ChatMessage> findRecentMessages(@Param("roomId") String roomId, @Param("startDate") LocalDateTime startDate);
}
