package com.meetup.teame.backend.domain.review.dto.response;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.review.entity.Review;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReviewRes {

    private Long id;

    private String description;

    private Long mentorId;

    private Long menteeId;

    //멘토 이름, 나이, 성별, 거주지, 프로필 사진, 경험 유형, 경험 제목

    public static ReviewRes of(Review review) {
        return ReviewRes.builder()
                .id(review.getId())
                .description(review.getDescription())
                .mentorId(review.getMentor().getUser().getId())
                .menteeId(review.getMentee().getMentee().getId())
                .build();
    }
}
