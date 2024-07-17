package com.ssafy.stompchat.board.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class BoardRequestDto {
    private String userId;
    private String title;
    private String content;
    private String category;
    private String imageURL;
    private boolean isBoardOpen;


}
