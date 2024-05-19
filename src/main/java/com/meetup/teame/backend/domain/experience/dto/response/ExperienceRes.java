package com.meetup.teame.backend.domain.experience.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ExperienceRes {
    private Long id;
    private String type;
    private String title;
    private String imageUrl;
    private String name;
    private Long age;
    private String gender;
    private String location;
    private String message;

    public static ExperienceRes of(Experience experience) {
        return ExperienceRes.builder()
                .id(experience.getId())
                .type(experience.getType().getDescription())
                .title(experience.getDescription())
                .imageUrl(experience.getUser().getImageUrl())
                .name(experience.getUser().getName())
                .age(experience.getUser().getAge())
                .gender(experience.getUser().getGender().getDescription())
                .location(experience.getUser().getLocation())
                .message(experience.getDetail())
                .build();
    }
}
