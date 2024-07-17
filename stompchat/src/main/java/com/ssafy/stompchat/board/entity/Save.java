package com.ssafy.stompchat.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Save {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saveId;
    private String boardId;
    private String userId;
    private LocalDateTime createdAt = LocalDateTime.now();
}
