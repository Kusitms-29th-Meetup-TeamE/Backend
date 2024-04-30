package com.meetup.teame.backend.domain.activity.entity;

import com.meetup.teame.backend.domain.like.entity.ActivityLike;
import com.meetup.teame.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Comment("활동")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("활동 제목")
    private String title;

    @Comment("활동 장소")
    private String location;

    @Comment("활동 일시")
    private LocalDateTime time;

    @Comment("참여 인원")
    private Long currentParticipants = 0L;

    @Comment("최대 참여 인원")
    private Long maxParticipants;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Personality> personalities = new ArrayList<>();

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<ActivityLike> activityLikes;

    public static Activity of(String title, String location, LocalDateTime time, Long maxParticipants, List<Personality> personalities) {
        return Activity.builder()
                .title(title)
                .location(location)
                .time(time)
                .maxParticipants(maxParticipants)
                .personalities(personalities)
                .build();
    }
}