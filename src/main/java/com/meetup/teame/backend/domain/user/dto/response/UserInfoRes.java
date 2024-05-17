package com.meetup.teame.backend.domain.user.dto.response;

import com.meetup.teame.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserInfoRes {

    private String name;

    private String email;

    private String imageUrl;

    private String location;

    public static UserInfoRes of(User user) {
        return UserInfoRes.builder()
                .name(user.getName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .location(user.getLocation())
                .build();
    }
}
