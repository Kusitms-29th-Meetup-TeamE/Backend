package com.meetup.teame.backend.domain.activity.entity;

import com.meetup.teame.backend.domain.chatting.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.user.entity.Personality;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;

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

    @Comment("활동 설명")
    private String description;

    @Comment("활동 제공 기관")
    private String agency;

    @Comment("기관 유형")
    @Enumerated(EnumType.STRING)
    private AgencyType agencyType;

    @Comment("활동 장소")
    private String location;

    @Comment("활동 일시")
    private LocalDateTime time;

    @Comment("활동 소요 시간(분)")
    private Long duration;

    @Comment("참여 인원")
    private Long currentParticipants;

    @Comment("최대 참여 인원")
    private Long maxParticipants;

    @Comment("썸네일 이미지")
    private String imageUrl;

    @ElementCollection
    @Comment("활동 사진들")
    private List<String> activityImgs;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Personality> personalities;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<ActivityLike> activityLikes;

    @OneToOne(mappedBy = "activity", cascade = CascadeType.ALL)
    private GroupChatRoom groupChatRoom;

    public static Activity of(String title, String description, String location, LocalDateTime time, Long maxParticipants, List<Personality> personalities, String imageUrl) {
        return Activity.builder()
                .title(title)
                .description(description)
                .location(location)
                .time(time)
                .currentParticipants(0L)
                .maxParticipants(maxParticipants)
                .personalities(personalities)
                .imageUrl(imageUrl)
                .build();
    }

    public void incrementCurrentParticipants() {
        this.currentParticipants++;
    }
}