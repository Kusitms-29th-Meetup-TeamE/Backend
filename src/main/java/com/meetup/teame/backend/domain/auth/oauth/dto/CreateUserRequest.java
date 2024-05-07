package com.meetup.teame.backend.domain.auth.oauth.dto;

import com.meetup.teame.backend.domain.user.entity.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateUserRequest {

    @Comment("사용자 이름")
    private String name;

    @Comment("사용자 이미지")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String location;

    //test
    private String birthyear;


    private String email;
}
