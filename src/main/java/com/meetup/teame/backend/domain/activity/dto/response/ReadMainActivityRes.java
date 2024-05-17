package com.meetup.teame.backend.domain.activity.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.personality.Personality;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ReadMainActivityRes {
    private List<String> personalities;
    private Boolean isLiked;
    private String title;
    private Long currentParticipants;
    private Long maxParticipants;
    private String location;
    private String time;

    public static ReadMainActivityRes of(Activity activity, Boolean isLiked) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", new Locale("ko", "KR"));
        return ReadMainActivityRes.builder()
                .personalities(
                        activity.getPersonalities().stream()
                                .map(Personality::getDescription)
                                .toList()
                )
                .isLiked(isLiked)
                .title(activity.getTitle())
                .currentParticipants(activity.getCurrentParticipants())
                .maxParticipants(activity.getMaxParticipants())
                .location(activity.getLocation())
                .time(activity.getTime().format(formatter))
                .build();
    }
}
