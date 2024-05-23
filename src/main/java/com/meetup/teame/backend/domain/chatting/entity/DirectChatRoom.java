package com.meetup.teame.backend.domain.chatting.entity;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("DIRECT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DirectChatRoom extends ChatRoom {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private User mentee;

    @Builder
    private DirectChatRoom(Experience experience, User mentee) {
        this.experience = experience;
        this.mentee = mentee;
    }

    public static DirectChatRoom of(Experience experience, User mentee) {
        return DirectChatRoom.builder()
                .experience(experience)
                .mentee(mentee)
                .build();
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
