package com.meetup.teame.backend.domain.chatting.repository.custom;

import com.meetup.teame.backend.domain.chatting.entity.GroupChatRoom;
import com.meetup.teame.backend.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.meetup.teame.backend.domain.chatting.entity.QGroupChatRoom.groupChatRoom;
import static com.meetup.teame.backend.domain.chatting.entity.QUserChatRoom.userChatRoom;
import static com.meetup.teame.backend.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class GroupChatRoomRepositoryImpl implements GroupChatRoomRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GroupChatRoom> findForUser(User who) {
        return jpaQueryFactory
                .selectFrom(groupChatRoom)
                .join(groupChatRoom.userChatRooms, userChatRoom)
                .join(userChatRoom.user, user)
                .where(user.eq(who))
                .orderBy(groupChatRoom.updatedAt.desc())
                .fetch();
    }

    @Override
    public List<GroupChatRoom> findUpdatableRooms() {
        return jpaQueryFactory
                .selectFrom(groupChatRoom)
                .where(groupChatRoom.nextAppointment.appointmentDate.before(LocalDate.now()))
                .fetch();
    }

    @Override
    public List<GroupChatRoom> findAppointmentForUserInMonth(User who, int year, int month) {
        return jpaQueryFactory
                .selectFrom(groupChatRoom)
                .join(groupChatRoom.userChatRooms, userChatRoom)
                .join(userChatRoom.user, user)
                .where(user.eq(who)
                        .and(groupChatRoom.nextAppointment.appointmentDate.year().eq(year))
                        .and(groupChatRoom.nextAppointment.appointmentDate.month().eq(month)))
                .orderBy(groupChatRoom.updatedAt.desc())
                .fetch();
    }

    @Override
    public List<GroupChatRoom> findActivityForUserInMonth(User who, int year, int month) {
        return jpaQueryFactory
                .selectFrom(groupChatRoom)
                .join(groupChatRoom.userChatRooms, userChatRoom)
                .join(userChatRoom.user, user)
                .where(user.eq(who)
                        .and(groupChatRoom.activity.time.year().eq(year))
                        .and(groupChatRoom.activity.time.month().eq(month)))
                .orderBy(groupChatRoom.updatedAt.desc())
                .fetch();
    }
}
