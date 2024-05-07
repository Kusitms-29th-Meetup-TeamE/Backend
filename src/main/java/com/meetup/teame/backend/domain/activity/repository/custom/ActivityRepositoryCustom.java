package com.meetup.teame.backend.domain.activity.repository.custom;

import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.user.entity.User;

import java.util.List;

public interface ActivityRepositoryCustom {
    List<Activity> findActivitiesForUser(User user);
}
