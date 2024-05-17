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

    private String agency;

    private String agencyType;

    private String location;

    private String time;

    private String activityThumbnail;

    private boolean isLiked;

    public static ActivitySummaryRes of(Activity activity, boolean isLiked) {

        List<String> activityImgs = activity.getActivityImgs();
        String activityThumbnail = null;
        //String activityThumbnail = activityImgs.get(0);
        if (!activityImgs.isEmpty()) {
            activityThumbnail = activityImgs.get(0);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", new Locale("ko", "KR"));

        return ActivitySummaryRes.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .agency(activity.getAgency())
                .agencyType(activity.getAgencyType().getDescription())
                .time(activity.getTime().format(formatter))
                .activityThumbnail(activityThumbnail)
                .isLiked(isLiked)
                .build();
    }
}
