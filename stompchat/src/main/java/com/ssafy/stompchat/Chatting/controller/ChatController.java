package com.ssafy.stompchat.Chatting.controller;

import com.ssafy.stompchat.Chatting.dto.Chat;
import com.ssafy.stompchat.Chatting.service.Chatservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatController {
    private final SimpMessageSendingOperations template;
    private final Chatservice chatservice;

    @MessageMapping("/enterUser")
    public void enterUser(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor){
        String userUUID = UUID.randomUUID().toString();
        chatservice.enterRoom(chat.getRoomId(),userUUID, chat.getSender());

        headerAccessor.getSessionAttributes().put("userUUID", userUUID);
        headerAccessor.getSessionAttributes().put("roomId", chat.getRoomId());

        System.out.println(chat.getRoomId());

        chat.setMessage(chat.getSender() + " 님 입장!!");
        chat.setType(Chat.MessageType.ENTER);
        template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);
    }
    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor) {
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");

        log.info("CHAT {}", chat);
        chat.setType(Chat.MessageType.TALK);
//        chatservice.incrementUnreadCount(roomId);
        chatservice.saveMessage(chat);
        template.convertAndSend("/sub/chat/room/" + roomId, chat);
//        chat.setMessage(chat.getMessage());
//        System.out.println(chat.toString());
//        template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);
    }

    @MessageMapping("/leaveUser")
    public void leaveUser(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor) {
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");

        if (roomId != null && userUUID != null) {
            chatservice.leaveRoom(roomId, userUUID);
            chat.setType(Chat.MessageType.LEAVE);
            chat.setMessage(chat.getSender() + " 님 퇴장!!");
            template.convertAndSend("/sub/chat/room/" + roomId, chat);
        }
    }

}
