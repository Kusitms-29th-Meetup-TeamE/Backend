package com.meetup.teame.backend.domain.review.service;

import com.meetup.teame.backend.domain.chatroom.entity.DirectChatRoom;
import com.meetup.teame.backend.domain.chatroom.repository.DirectChatRoomRepository;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.repository.ExperienceRepository;
import com.meetup.teame.backend.domain.review.dto.request.ReviewReq;
import com.meetup.teame.backend.domain.review.dto.response.ReviewRes;
import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.review.repository.ReviewRepository;
import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ExperienceRepository experienceRepository;
    private final DirectChatRoomRepository directChatRoomRepository;

    //후기 작성하기
    public ReviewRes createReview(ReviewReq reviewReq) {
        Experience mentor = experienceRepository.findById(reviewReq.getMentorId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_EXPERIENCE));
        DirectChatRoom mentee = directChatRoomRepository.findById(reviewReq.getMenteeId())
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_CHAT_ROOM));

        Review review = reviewRepository.save(Review.of(reviewReq.getDescription(), mentor, mentee));

        return ReviewRes.of(review);
    }

    //후기 조회하기
    public ReviewRes findReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_REVIEW));
        return ReviewRes.of(review);
    }

}
