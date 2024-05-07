package com.meetup.teame.backend.domain.activity.repository;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.activity.repository.custom.ActivityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long>, ActivityRepositoryCustom {
}
