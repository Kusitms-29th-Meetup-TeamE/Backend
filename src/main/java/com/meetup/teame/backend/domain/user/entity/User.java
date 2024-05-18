package com.meetup.teame.backend.domain.user.entity;

import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.UserChatRoom;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.like.entity.ActivityLike;
import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.user.dto.request.UpdateUserReq;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Comment("사용자")
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자 이름")
    private String name;

    @Comment("사용자 이메일")
    private String email;

    @Comment("사용자 비밀번호")
    private String password;

    @Comment("사용자 이미지")
    private String imageUrl;

    @Comment("사용자 나이")
    private Long age;

    @Comment("사용자 성별")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Comment("사용자 동네")
    private String location;

    @Comment("사용자 포인트")
    private Long point;

    @Comment("사용자 한마디")
    private String oneWord;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ActivityLike> activityLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserChatRoom> userChatRooms;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Personality> personalities;

    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL)
    private List<DirectChatRoom> learnedChatRooms;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Review> reviewsAboutMe;

    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL)
    private List<Review> reviewsByMe;


    public void setPersonalities(List<Personality> personalities) {
        this.personalities = personalities;
    }

    public void setOneWord(String oneWord) {
        this.oneWord = oneWord;
    }

    public void update(UpdateUserReq request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.imageUrl = request.getImageUrl();
        this.location = request.getLocation();
    }
}
