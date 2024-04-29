package com.meetup.teame.backend.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ExceptionRes {
    private String message;

    public static ExceptionRes of(String message) {
        return ExceptionRes.builder()
                .message(message)
                .build();
    }
}
