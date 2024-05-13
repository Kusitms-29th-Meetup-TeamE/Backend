package com.meetup.teame.backend.domain.activity.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.personality.Personality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ActivityDetailsRes {

    private Long id;

    private String title;

    private String agency;

    private String location;

    private LocalDateTime time;

    private Long currentParticipants;

    private Long maxParticipants;

    private List<Personality> personalities;

    private List<String> activityImgs;

    public static ActivityDetailsRes of(Activity activity, List<String> activityImgs) {
        return ActivityDetailsRes.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .agency(activity.getAgency())
                .location(activity.getLocation())
                .time(activity.getTime())
                .currentParticipants(activity.getCurrentParticipants())
                .maxParticipants(activity.getMaxParticipants())
                .personalities(activity.getPersonalities())
                .activityImgs(activityImgs)
                .build();
    }
}
