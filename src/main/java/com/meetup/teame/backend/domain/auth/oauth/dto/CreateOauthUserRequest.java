package com.meetup.teame.backend.domain.auth.oauth.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateOauthUserRequest {
    private String name; // 필수 동의
    private String email; // 선택 동의
    private String gender; // 필수 동의
    private String birthYear; // 필수 동의
    private String profileImage; // 선택 동의
}