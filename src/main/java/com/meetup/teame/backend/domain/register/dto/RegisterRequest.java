package com.meetup.teame.backend.domain.register.dto;


import com.meetup.teame.backend.domain.user.entity.Gender;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private String gender;

    private String birthyear;

    private String location;

    public User toEntity(RegisterRequest request, String encodedPassword, long age) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .age(age)
                .gender(Objects.equals(request.getGender(), "male") ? Gender.MALE:Gender.FEMALE)
                .location(request.getLocation())
                .point(0L)
                .build();
    }
}

