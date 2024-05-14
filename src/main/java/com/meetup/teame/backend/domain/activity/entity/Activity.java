package com.meetup.teame.backend.domain.activity.entity;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.like.entity.ActivityLike;
import com.meetup.teame.backend.domain.personality.Personality;
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

    @Comment("활동 제공 기관")
    private String agency;

    @Comment("활동 장소")
    private String location;

    @Comment("활동 일시")
    private LocalDateTime time;

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

    public static Activity of(String title, String location, LocalDateTime time, Long maxParticipants, List<Personality> personalities, String imageUrl) {
        return Activity.builder()
                .title(title)
                .location(location)
                .time(time)
                .currentParticipants(0L)
                .maxParticipants(maxParticipants)
                .personalities(personalities)
                .imageUrl(imageUrl)
                .build();
    }
}