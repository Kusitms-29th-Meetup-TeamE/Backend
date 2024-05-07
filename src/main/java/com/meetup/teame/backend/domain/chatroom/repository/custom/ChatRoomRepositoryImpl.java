package com.meetup.teame.backend.domain.chatroom.repository.custom;

import com.meetup.teame.backend.domain.chatroom.entity.ChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.ChatType;
import com.meetup.teame.backend.domain.chatroom.entity.QChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.QUserChatRoom;
import com.meetup.teame.backend.domain.user.entity.QUser;
import com.meetup.teame.backend.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.meetup.teame.backend.domain.chatroom.entity.QChatRoom.chatRoom;
import static com.meetup.teame.backend.domain.chatroom.entity.QUserChatRoom.userChatRoom;
import static com.meetup.teame.backend.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChatRoom> findChatRoomsForUser(User who, ChatType chatType) {
        return jpaQueryFactory
                .selectFrom(chatRoom)
                .join(chatRoom.userChatRooms, userChatRoom)
                .join(userChatRoom.user, user)
                .where(chatRoom.chatType.eq(chatType)
                        .and(user.eq(who)))
                .orderBy(chatRoom.lastChatTime.desc())
                .fetch();
    }
}
