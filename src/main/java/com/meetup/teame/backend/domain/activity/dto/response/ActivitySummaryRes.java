package com.meetup.teame.backend.domain.activity.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.like.repository.ActivityLikeRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ActivitySummaryRes {

    private Long id;

    private String title;

    private String agency;

    private String agencyType;

    private String location;

    private LocalDateTime time;

    private String activityThumbnail;

    private boolean isLiked = false;

    public static ActivitySummaryRes of(Activity activity, boolean isLiked) {

        List<String> activityImgs = activity.getActivityImgs();
        String activityThumbnail = null;
        //String activityThumbnail = activityImgs.get(0);
        if (!activityImgs.isEmpty()) {
            activityThumbnail = activityImgs.get(0);
        }

        return ActivitySummaryRes.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .agency(activity.getAgency())
                .agencyType(activity.getAgencyType().getDescription())
                .time(activity.getTime())
                .activityThumbnail(activityThumbnail)
                .isLiked(isLiked)
                .build();
    }
}
