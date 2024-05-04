package com.meetup.teame.backend.domain.user.dto.oauth;

import lombok.Getter;

@Getter
public class KakaoUserDto {

    private Long id;

    private String name;

    private String KakaoId;

    private String gender;

    private String birthyear;

    private String role;

    public KakaoUserDto(Long id, String nickname, String kakaoId, String gender, String birthyear) {
        this.id = id;
        this.name = nickname;
        this.KakaoId = kakaoId;
        this.gender = gender;
        this.birthyear = birthyear;
    }

    public KakaoUserDto(Long id, String role) {
        this.id = id;
        this.role = role;
    }
}
