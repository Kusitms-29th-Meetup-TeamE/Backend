package com.meetup.teame.backend.domain.user.repository;

import com.meetup.teame.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByKakaoId(String kakaoId);
}
