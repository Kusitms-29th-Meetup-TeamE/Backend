package com.meetup.teame.backend.domain.review.service;

import com.meetup.teame.backend.domain.auth.jwt.SecurityContextProvider;
import com.meetup.teame.backend.domain.review.dto.request.CreateReviewReq;
import com.meetup.teame.backend.domain.review.dto.response.ReadReviewsByMeRes;
import com.meetup.teame.backend.domain.review.dto.response.ReviewByMeRes;
import com.meetup.teame.backend.domain.review.entity.Review;
import com.meetup.teame.backend.domain.review.repository.ReviewRepository;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    //후기 작성하기
    public void sendReview(CreateReviewReq createReviewReq, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_REVIEW));
        if (review.getIsWritten())
            throw new CustomException(ExceptionContent.BAD_REQUEST_ALREADY_WRITTEN_REVIEW);
        review.setContent(createReviewReq.getContent());
    }

    public ReadReviewsByMeRes readReviewsByMe() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_USER));
        return ReadReviewsByMeRes.of(reviewRepository.findByMentee(user));
    }

    public ReviewByMeRes readReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionContent.NOT_FOUND_REVIEW));
        return ReviewByMeRes.of(review);
    }
}
