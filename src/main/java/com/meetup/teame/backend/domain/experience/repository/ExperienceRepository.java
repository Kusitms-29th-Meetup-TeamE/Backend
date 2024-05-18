package com.meetup.teame.backend.domain.experience.repository;

import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.repository.custom.ExperienceRepositoryCustom;
import com.meetup.teame.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long>, ExperienceRepositoryCustom {
    List<Experience> findAllByUser(User user);
}
