package com.meetup.teame.backend.domain.activity.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReadActivitiesRes {
    private Long curPage;
    private Long pageCount;
    private List<ActivitySummaryRes> activitySummaries;

    public static ReadActivitiesRes of(Long curPage, Long pageCount, List<ActivitySummaryRes> activitySummaries) {
        return ReadActivitiesRes.builder()
                .curPage(curPage)
                .pageCount(pageCount)
                .activitySummaries(activitySummaries)
                .build();
    }
}
