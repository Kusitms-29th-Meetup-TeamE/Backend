package com.meetup.teame.backend.domain.activity.repository.custom;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.entity.AgencyType;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.domain.user.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.meetup.teame.backend.domain.activity.entity.QActivity.activity;
import static com.meetup.teame.backend.domain.like.entity.QActivityLike.activityLike;

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
    public List<Activity> findByAgencyAndPersonalities(long offset, long limit, AgencyType agencyType, List<Personality> personalities) {
        BooleanBuilder builder = new BooleanBuilder();

        // agencyType나 personalities가 입력된 경우 해당 값으로 필터링
        if (agencyType != null || (personalities != null && !personalities.isEmpty())) {
            if (agencyType != null) {
                builder.and(activity.agencyType.eq(agencyType));
            }
            if (personalities != null && !personalities.isEmpty()) {
                builder.and(activity.personalities.any().in(personalities));
            }
        }

        return jpaQueryFactory
                .selectFrom(activity)
                .where(builder)
                .orderBy(activity.id.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Activity> findLikedActivities(Long userId, long offset, long limit, AgencyType agencyType, List<Personality> personalities) {
        BooleanBuilder builder = new BooleanBuilder();

        // 사용자의 좋아하는 활동 ID 목록을 조회
        List<Long> likedActivityIds = jpaQueryFactory
                .select(activityLike.activity.id)
                .from(activityLike)
                .where(activityLike.user.id.eq(userId))
                .fetch();

        // 좋아하는 활동 ID가 있는 경우만 필터 조건에 추가
        if (!likedActivityIds.isEmpty()) {
            builder.and(activity.id.in(likedActivityIds));
        }

        // agencyType나 personalities가 입력된 경우 해당 값으로 필터링
        if (agencyType != null) {
            builder.and(activity.agencyType.eq(agencyType));
        }
        if (personalities != null && !personalities.isEmpty()) {
            builder.and(activity.personalities.any().in(personalities));
        }

        return jpaQueryFactory
                .selectFrom(activity)
                .where(builder)
                .orderBy(activity.id.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }


    @Override
    public Long countActivities(AgencyType agencyType, List<Personality> personalities) {
        BooleanBuilder builder = new BooleanBuilder();

        // agencyType나 personalities가 입력된 경우 해당 값으로 필터링
        if (agencyType != null || (personalities != null && !personalities.isEmpty())) {
            if (agencyType != null) {
                builder.and(activity.agencyType.eq(agencyType));
            }
            if (personalities != null && !personalities.isEmpty()) {
                builder.and(activity.personalities.any().in(personalities));
            }
        }

        return jpaQueryFactory
                .select(activity.count())
                .from(activity)
                .where(builder)
                .fetchOne();
    }
}
