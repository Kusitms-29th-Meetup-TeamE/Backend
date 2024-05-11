package com.meetup.teame.backend.domain.activity.dto.response;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ActivitySummaryRes {

    private Long id;

    private String title;

    private String agency;

    private String location;

    private LocalDateTime time;

    //나중에 어떤 유저가 좋아요를 눌렀는지 안눌렀는지 체크하는거 해야함
    private boolean isLiked;

    public static ActivitySummaryRes of(Activity activity) {
        return ActivitySummaryRes.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .agency(activity.getAgency())
                .time(activity.getTime())
                .build();
    }
}
