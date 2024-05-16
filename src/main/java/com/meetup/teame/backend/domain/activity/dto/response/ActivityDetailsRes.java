package com.meetup.teame.backend.domain.activity.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.personality.Personality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ActivityDetailsRes {

    private Long id;

    private String title;

    private String agency;

    private String agencyType;

    private String location;

    private String time;

    private Long currentParticipants;

    private Long maxParticipants;

    private List<Personality> personalities;

    private List<String> activityImgs;

    public static ActivityDetailsRes of(Activity activity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", new Locale("ko", "KR"));

        return ActivityDetailsRes.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .agency(activity.getAgency())
                .agency(activity.getAgencyType().getDescription())
                .location(activity.getLocation())
                .time(activity.getTime().format(formatter))
                .currentParticipants(activity.getCurrentParticipants())
                .maxParticipants(activity.getMaxParticipants())
                .personalities(activity.getPersonalities())
                .activityImgs(activity.getActivityImgs())
                .build();
    }
}
