package com.meetup.teame.backend.domain.chatroom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Comment("채팅방")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("채팅방 유형 (그룹, 1:1)")
    @Enumerated(EnumType.STRING)
    private ChatType type;

    @Comment("채팅방 이미지")
    private String imageUrl;//단체방일때만 필요

    @Comment("채팅방 제목")
    private String title;

    @Comment("최근 만남 날짜")
    private LocalDate lastMeetingDate;//단체방일때만 필요

    @Comment("약속 날짜")
    private LocalDateTime appointmentDate;

    @Comment("최근 메세지")
    private String lastMessage;

    @OneToMany(mappedBy = "ChatRoom", cascade = CascadeType.ALL)
    List<UserChatRoom> userChatRooms;
}
