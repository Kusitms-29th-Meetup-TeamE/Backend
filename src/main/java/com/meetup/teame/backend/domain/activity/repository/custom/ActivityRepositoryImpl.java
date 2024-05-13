package com.meetup.teame.backend.domain.activity.repository.custom;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.entity.QActivity;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.meetup.teame.backend.domain.activity.entity.QActivity.activity;

@RequiredArgsConstructor
public class ActivityRepositoryImpl implements ActivityRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Activity> findActivitiesForUser(User user) {
        return jpaQueryFactory
                .select(activity)
                .from(activity)
                .where(activity.personalities.any().in(user.getPersonalities()))
                .fetch();
    }

    @Override
    public List<Activity> findByAgencyAndPersonality(String agency, Personality personality) {
        QActivity activity = QActivity.activity;
        return jpaQueryFactory
                .selectFrom(activity)
                .where(activity.agency.eq(agency)
                        .and(activity.personalities.contains(personality)))
                .fetch();
    }
}
