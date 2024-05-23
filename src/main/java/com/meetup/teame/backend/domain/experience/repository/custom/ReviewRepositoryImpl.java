package com.meetup.teame.backend.domain.experience.repository.custom;

import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import com.meetup.teame.backend.domain.experience.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.meetup.teame.backend.domain.experience.entity.QReview.review;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> findReviewsByUserId(Long userId, String type) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.mentor.id.eq(userId));

        if (type != null) {
            builder.and(review.appointmentType.eq(ExperienceType.of(type)));
        }

        return jpaQueryFactory
                .selectFrom(review)
                .where(builder)
                .orderBy(review.id.desc())
                .fetch();
    }

}
