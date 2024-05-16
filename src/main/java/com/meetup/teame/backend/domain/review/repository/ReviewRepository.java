package com.meetup.teame.backend.domain.review.repository;

import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.review.repository.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
