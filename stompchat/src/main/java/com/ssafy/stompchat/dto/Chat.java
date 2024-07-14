package com.ssafy.stompchat.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    public enum MessageType{
        ENTER, TALK, LEAVE;
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String time;
}
