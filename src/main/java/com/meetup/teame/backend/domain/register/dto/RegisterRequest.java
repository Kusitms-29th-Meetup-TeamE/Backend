package com.meetup.teame.backend.domain.register.dto;


import com.meetup.teame.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private String birthyear;

    private String gender;

    private String location;

    public User toEntity(RegisterRequest request, String encodedPassword) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)

                .location(request.getLocation())
                .build();
    }
}

