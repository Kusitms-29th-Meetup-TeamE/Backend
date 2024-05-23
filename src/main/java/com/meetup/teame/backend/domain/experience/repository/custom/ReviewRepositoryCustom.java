package com.meetup.teame.backend.domain.experience.repository.custom;

import com.meetup.teame.backend.domain.experience.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findReviewsByUserId(Long userId, String type);
}
