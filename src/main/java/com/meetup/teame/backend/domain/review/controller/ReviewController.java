package com.meetup.teame.backend.domain.review.controller;

import com.meetup.teame.backend.domain.review.dto.request.CreateReviewReq;
import com.meetup.teame.backend.domain.review.dto.response.ReadReviewsByMeRes;
import com.meetup.teame.backend.domain.review.dto.response.ReviewByMeRes;
import com.meetup.teame.backend.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "review", description = "후기 관련 api")
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "후기 보내기", description = """
            후기 보내기 api 입니다.
            
            request body에 리뷰 내용을 입력해 전달해주세요.
            """)
    //후기 보내기
    @PostMapping("/{reviewId}")
    public ResponseEntity<Void> sendReview(@RequestBody CreateReviewReq request, @PathVariable Long reviewId) {
        reviewService.sendReview(request,reviewId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "후기 보내기 페이지에서 해당 배움 정보 보기", description = """
            후기 보내기 페이지에서 해당 배움 정보 보기 api 입니다.
            """)
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewByMeRes> readReview(@PathVariable Long reviewId) {
        return ResponseEntity
                .ok(reviewService.readReview(reviewId));
    }

    @Operation(summary = "나의 배움 내역 보기", description = """
            나의 배움 내역 보기 api 입니다.
            """)
    @GetMapping("/byme")
    public ResponseEntity<ReadReviewsByMeRes> readReviewsByMe() {
        return ResponseEntity
                .ok(reviewService.readReviewsByMe());
    }

//    @Operation(summary = "나의 후기 보기", description = """
//            나의 후기 보기 api 입니다.
//            """)
//    @GetMapping("/aboutme")
//    public ResponseEntity<ReadReviewsAboutMeRes> readReviewsAboutMe() {
//        //todo api 작성
//        return null;
////        return ResponseEntity
////                .ok(reviewService.readReviewsAboutMe());
//    }
}
