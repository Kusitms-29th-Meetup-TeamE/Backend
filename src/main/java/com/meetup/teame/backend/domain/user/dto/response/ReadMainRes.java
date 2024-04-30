package com.meetup.teame.backend.domain.user.dto.response;

import com.meetup.teame.backend.domain.activity.dto.response.ReadMainActivityRes;
import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.experience.dto.response.ReadMainExperienceRes;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ReadMainRes {
    private List<ReadMainActivityRes> activities;
    private List<ReadMainExperienceRes> experiences;
    private Integer point;

    public static ReadMainRes of(List<Activity> activities, List<Experience> experiences, Integer point) {
        return ReadMainRes.builder()
                .activities(
                        activities.stream()
                                .map(activity -> ReadMainActivityRes.of(activity, false))
                                .toList()
                )
                .experiences(
                        experiences.stream()
                                .map(ReadMainExperienceRes::of)
                                .toList()
                )
                .point(point)
                .build();
    }
}
