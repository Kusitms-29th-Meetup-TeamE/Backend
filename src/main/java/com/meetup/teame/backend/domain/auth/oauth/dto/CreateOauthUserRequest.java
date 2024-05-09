package com.meetup.teame.backend.domain.auth.oauth.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateOauthUserRequest {
    private String name;
    private String email;
    private String gender;
    private String birthyear;
    private String profileImage;
}