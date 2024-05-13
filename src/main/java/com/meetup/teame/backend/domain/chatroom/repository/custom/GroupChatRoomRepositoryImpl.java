package com.meetup.teame.backend.domain.chatroom.repository.custom;

import com.meetup.teame.backend.domain.chatroom.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.QGroupChatRoom;
import com.meetup.teame.backend.domain.chatroom.entity.QUserChatRoom;
import com.meetup.teame.backend.domain.user.entity.QUser;
import com.meetup.teame.backend.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.meetup.teame.backend.domain.chatroom.entity.QGroupChatRoom.groupChatRoom;
import static com.meetup.teame.backend.domain.chatroom.entity.QUserChatRoom.userChatRoom;
import static com.meetup.teame.backend.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class GroupChatRoomRepositoryImpl implements GroupChatRoomRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    //todo 최신순으로 정렬
    @Override
    public List<GroupChatRoom> findForUser(User who) {
        return jpaQueryFactory
                .selectFrom(groupChatRoom)
                .join(groupChatRoom.userChatRooms, userChatRoom)
                .join(userChatRoom.user, user)
                .where(user.eq(who))
                .fetch();
    }
}
