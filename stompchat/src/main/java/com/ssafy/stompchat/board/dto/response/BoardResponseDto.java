package com.ssafy.stompchat.board.dto.response;

import com.ssafy.stompchat.response.ApiResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
public class BoardResponseDto {
    private Long boardId;
    private String userId;
    private String title;
    private String content;
    private String category;
    private String imageURL;
    private boolean isBoardOpen;
    private LocalDateTime createdAt;

    // Getters and Setters
}