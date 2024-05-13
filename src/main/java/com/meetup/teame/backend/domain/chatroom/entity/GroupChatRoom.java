package com.meetup.teame.backend.domain.chatroom.entity;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("GROUP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GroupChatRoom extends ChatRoom {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Builder
    private GroupChatRoom(Activity activity) {
        this.activity = activity;
    }

    public static GroupChatRoom of(Activity activity) {
        return GroupChatRoom.builder()
                .activity(activity)
                .build();
    }
}
