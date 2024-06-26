package com.meetup.teame.backend.domain.chatting.repository.custom;

import com.meetup.teame.backend.domain.chatting.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.meetup.teame.backend.domain.chatting.entity.QDirectChatRoom.directChatRoom;
import static com.meetup.teame.backend.domain.chatting.entity.QUserChatRoom.userChatRoom;
import static com.meetup.teame.backend.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class DirectChatRoomRepositoryImpl implements DirectChatRoomRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<DirectChatRoom> findByMentorAndMentee(User mentor, User mentee) {
        DirectChatRoom result = jpaQueryFactory
                .selectFrom(directChatRoom)
                .where(directChatRoom.experience.user.eq(mentor)
                        .and(directChatRoom.mentee.eq(mentee)))
                .fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public List<DirectChatRoom> findForUser(User who) {
        return jpaQueryFactory
                .selectFrom(directChatRoom)
                .join(directChatRoom.userChatRooms, userChatRoom)
                .join(userChatRoom.user, user)
                .where(user.eq(who))
                .orderBy(directChatRoom.updatedAt.desc())
                .fetch();
    }

    @Override
    public List<DirectChatRoom> findUpdatableRooms() {
        return jpaQueryFactory
                .selectFrom(directChatRoom)
                .where(directChatRoom.nextAppointment.appointmentDate.before(LocalDate.now()))
                .fetch();
    }

    @Override
    public List<DirectChatRoom> findAppointmentForUserInMonth(User who, int year, int month) {
        return jpaQueryFactory
                .selectFrom(directChatRoom)
                .join(directChatRoom.userChatRooms, userChatRoom)
                .join(userChatRoom.user, user)
                .where(user.eq(who)
                        .and(directChatRoom.nextAppointment.appointmentDate.year().eq(year))
                        .and(directChatRoom.nextAppointment.appointmentDate.month().eq(month)))
                .orderBy(directChatRoom.updatedAt.desc())
                .fetch();
    }
}
