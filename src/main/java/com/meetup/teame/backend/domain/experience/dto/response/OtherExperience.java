package com.meetup.teame.backend.domain.experience.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class OtherExperience {
    private String type;
    private String title;

    public static OtherExperience of(Experience experience) {
        return OtherExperience.builder()
                .type(experience.getType().getDescription())
                .title(experience.getDescription())
                .build();
    }
}
