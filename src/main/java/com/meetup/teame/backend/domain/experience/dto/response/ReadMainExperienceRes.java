package com.meetup.teame.backend.domain.experience.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ReadMainExperienceRes {
    private String type;
    private String imageUrl;
    private String name;
    private Long age;
    private String gender;
    private String location;
    private String message;

    public static ReadMainExperienceRes of(Experience experience) {
        User user = experience.getUser();
        return ReadMainExperienceRes.builder()
                .type(experience.getType().getDescription())
                .imageUrl(user.getImageUrl())
                .name(user.getName())
                .age(user.getAge())
                .gender(user.getGender().getDescription())
                .location(user.getLocation())
                .message(experience.getDescription())
                .build();
    }
}
