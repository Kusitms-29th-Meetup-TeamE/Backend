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

    private String name;

    private String email;

    private String imageUrl;

    /*private String gender;

    private String birthyear;*/

    private String location;
}
