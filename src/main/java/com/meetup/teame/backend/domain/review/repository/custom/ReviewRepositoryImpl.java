package com.meetup.teame.backend.domain.review.repository.custom;

import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import com.meetup.teame.backend.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.meetup.teame.backend.domain.experience.entity.QExperience.experience;
import static com.meetup.teame.backend.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

//    @Override
//    public List<Review> findReviewsByUserId(Long userId, String type) {
//        BooleanBuilder builder = new BooleanBuilder();
//
//        builder.and(experience.user.id.eq(userId));
//
//        if (type != null) {
//            builder.and(experience.type.eq(ExperienceType.of(type)));
//        }
//
//        return jpaQueryFactory
//                .selectFrom(review)
//                .join(review.mentor, experience)
//                .fetchJoin()
//                .where(builder)
//                .fetch();
//    }

}
