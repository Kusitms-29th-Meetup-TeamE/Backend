package com.meetup.teame.backend.domain.auth.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOauthUserRequest {
    private String email; // 선택 동의
    private String gender; // 필수 동의
    private String nickname; // 필수 동의
    private String name; // 필수 동의
    private String birthYear; // 필수 동의
    private String profileImage; // 선택 동의
}