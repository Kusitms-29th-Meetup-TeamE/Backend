package com.meetup.teame.backend.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserReq {

    private String name;

    private String email;

    private String imageUrl;

    private String location;

    public void setImageUrl(String defaultImageUrl) {
        this.imageUrl=defaultImageUrl;
    }
}
