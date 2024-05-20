package com.meetup.teame.backend.domain.activity.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

@AllArgsConstructor
@Getter
@Builder
public class ActivitySummaryRes {

    private Long id;

    private String title;

    private String personality;

    private String agency;

    private String agencyType;

    private String location;

    private String time;

    private String activityThumbnail;

    private boolean isLiked;

    public static ActivitySummaryRes of(Activity activity, boolean isLiked) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", new Locale("ko", "KR"));

        return ActivitySummaryRes.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .personality(activity.getPersonalities().get(0).getDescription())
                .agency(activity.getAgency())
                .agencyType(activity.getAgencyType().getDescription())
                .location(activity.getLocation())
                .time(activity.getTime().format(formatter))
                .activityThumbnail(activity.getImageUrl())
                .isLiked(isLiked)
                .build();
    }
}
