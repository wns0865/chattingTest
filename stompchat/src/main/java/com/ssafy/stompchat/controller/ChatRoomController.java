package com.ssafy.stompchat.controller;

import com.ssafy.stompchat.dto.ChatRoom;
import com.ssafy.stompchat.dto.ResultResponse;
import com.ssafy.stompchat.repository.ChatRepository;
import com.ssafy.stompchat.service.Chatservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private static final String CREATE_ROOM_SUCCESS = "채팅방이 성공적으로 생성되었습니다.";
    private static final String FETCH_ROOMS_SUCCESS = "채팅방 목록을 성공적으로 가져왔습니다.";
    private final Chatservice chatservice;

    // 채팅 리스트 화면
    @GetMapping("/")
    public ResponseEntity<ResultResponse<List<ChatRoom>>> goChatRoom(){
        List<ChatRoom> chatRooms = chatservice.findAllRoom();
        return ResponseEntity.ok(ResultResponse.success(200, FETCH_ROOMS_SUCCESS, chatRooms));
    }

    @PostMapping("/room")
    public ResponseEntity<ResultResponse<String>> createRoom(@RequestParam String name) {
        ChatRoom room = chatservice.createChatRoom(name);
        return ResponseEntity.ok(ResultResponse.success(201, CREATE_ROOM_SUCCESS, room.getRoomId()));
    }

    // 채팅에 참여한 유저 리스트 반환
    @GetMapping("/userlist")
    public ResponseEntity<List<String>> userList(@RequestParam String roomId) {

        return ResponseEntity.ok(chatservice.getUserList(roomId));
    }
}
