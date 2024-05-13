package com.meetup.teame.backend.domain.experience.dto.response;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ReadExperiencesRes {
    private Long curPage;
    private Long pageCount;
    private List<ExperienceRes> experiences;

    public static ReadExperiencesRes of(Long curPage, Long pageCount, List<Experience> experiences){
        return ReadExperiencesRes.builder()
                .curPage(curPage)
                .pageCount(pageCount)
                .experiences(experiences.stream()
                        .map(ExperienceRes::of)
                        .toList())
                .build();
    }
}
