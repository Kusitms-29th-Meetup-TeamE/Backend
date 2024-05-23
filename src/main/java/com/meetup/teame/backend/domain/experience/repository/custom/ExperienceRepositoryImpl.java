package com.meetup.teame.backend.domain.experience.repository.custom;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import com.meetup.teame.backend.domain.experience.entity.QExperience;
import com.meetup.teame.backend.domain.user.entity.QUser;
import com.meetup.teame.backend.domain.user.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.meetup.teame.backend.domain.experience.entity.QExperience.experience;
import static com.meetup.teame.backend.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements ExperienceRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Experience> findExperiencesOrderByLatest(long offset, long limit, String category, User me) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(experience.user.ne(me));
        if (category != null) {
            builder.and(experience.type.eq(ExperienceType.of(category)));
        }
        return jpaQueryFactory
                .selectFrom(experience)
                .join(experience.user, user)
                .fetchJoin()
                .where(builder)
                .orderBy(experience.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Experience> findExperiencesOrderByReview(long offset, long limit, String category, User me) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(experience.user.ne(me));
        if (category != null)
            builder.and(experience.type.eq(ExperienceType.of(category)));
        return jpaQueryFactory
                .selectFrom(experience)
                .join(experience.user, user)
                .fetchJoin()
                .where(builder)
                .orderBy(experience.reviewCount.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public Long countExperiences(String category) {
        BooleanBuilder builder = new BooleanBuilder();
        if (category != null)
            builder.and(experience.type.eq(ExperienceType.of(category)));
        return jpaQueryFactory
                .select(experience.count())
                .from(experience)
                .where(builder)
                .fetchOne();
    }

    @Override
    public List<Experience> findExperiencesForMain() {
        return jpaQueryFactory
                .selectFrom(experience)
                .orderBy(experience.createdAt.desc())
                .limit(4)
                .fetch();
    }
}
