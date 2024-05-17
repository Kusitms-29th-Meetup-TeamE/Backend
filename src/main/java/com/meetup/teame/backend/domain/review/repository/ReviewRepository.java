package com.meetup.teame.backend.domain.review.repository;

import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.review.repository.custom.ReviewRepositoryCustom;
import com.meetup.teame.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    List<Review> findByMentee(User mentee);
}
