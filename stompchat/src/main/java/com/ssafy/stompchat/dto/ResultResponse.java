package com.ssafy.stompchat.dto;

import lombok.Getter;
import lombok.Builder;
import java.time.LocalDateTime;

@Getter
@Builder
public class ResultResponse<T> {
    private final boolean success;
    private final int status;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    public static <T> ResultResponse<T> of(boolean success, int status, String message, T data) {
        return ResultResponse.<T>builder()
                .success(success)
                .status(status)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ResultResponse<T> success(int status, String message, T data) {
        return of(true, status, message, data);
    }

    public static <T> ResultResponse<T> error(int status, String message) {
        return of(false, status, message, null);
    }
}