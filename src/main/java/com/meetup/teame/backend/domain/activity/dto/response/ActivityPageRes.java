package com.meetup.teame.backend.domain.activity.dto.response;

import java.util.List;

public record ActivityPageRes(
        List<ActivitySummaryRes> activities,
        int totalPages,
        long totalElements)
{

}
