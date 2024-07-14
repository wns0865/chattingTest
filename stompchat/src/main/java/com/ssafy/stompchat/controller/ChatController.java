package com.ssafy.stompchat.controller;

import com.ssafy.stompchat.dto.Chat;
import com.ssafy.stompchat.dto.ChatRoom;
import com.ssafy.stompchat.repository.ChatRepository;
import com.ssafy.stompchat.service.Chatservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {
    private final SimpMessageSendingOperations template;
    private final Chatservice chatservice;

    @MessageMapping("/enterUser")
    public void enterUser(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor){
        chatservice.plusUserCnt(chat.getRoomId());
        String userUUID = chatservice.addUser(chat.getRoomId(),chat.getSender());

        headerAccessor.getSessionAttributes().put("userUUID", userUUID);
        headerAccessor.getSessionAttributes().put("roomId", chat.getRoomId());
        System.out.println(chat.getRoomId());
        chat.setMessage(chat.getSender() + " 님 입장!!");
        template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);
    }
    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Chat chat) {
        log.info("CHAT {}", chat);
        chat.setMessage(chat.getMessage());
        System.out.println(chat.toString());
        template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);

    }

}
