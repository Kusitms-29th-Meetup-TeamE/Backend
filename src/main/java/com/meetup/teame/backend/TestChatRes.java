package com.meetup.teame.backend;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class TestChatRes {
    private String message;

    public static TestChatRes of(String message) {
        return TestChatRes.builder()
                .message(message)
                .build();
    }
}
