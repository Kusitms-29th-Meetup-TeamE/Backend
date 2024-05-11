package com.meetup.teame.backend.domain.experience.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ExperiencesRes {
    private Long curPage;
    private Long maxPage;
    private List<ExperienceRes> experiences;
}
