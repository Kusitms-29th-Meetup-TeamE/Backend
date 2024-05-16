package com.meetup.teame.backend.domain.like.repository;

import com.meetup.teame.backend.domain.like.entity.ActivityLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityLikeRepository extends JpaRepository<ActivityLike, Long> {

    boolean existsByUserIdAndActivityId(Long userId, Long activityId);

    @Query("SELECT al.activity.id FROM ActivityLike al WHERE al.user.id = :userId")
    List<Long> findLikedActivityIdsByUserId(Long userId);

}
