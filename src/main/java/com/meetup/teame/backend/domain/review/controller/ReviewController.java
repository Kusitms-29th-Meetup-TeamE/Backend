package com.meetup.teame.backend.domain.review.controller;

import com.meetup.teame.backend.domain.review.dto.request.ReviewReq;
import com.meetup.teame.backend.domain.review.dto.response.ReviewRes;
import com.meetup.teame.backend.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "review", description = "후기 관련 api")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "후기 보내기", description = """
            후기 보내기 api 입니다.
            
            후기 내용, 멘토 id, 멘티 id를 입력하셔야합니다.
            
            후기보내기가 정상적으로 요청되면 후기 id, 후기 내용, 멘토 id, 멘티 id을 반환해줍니다.
            """)
    //후기 보내기
    @PostMapping("/review")
    public ResponseEntity<ReviewRes> sendReview(@RequestBody ReviewReq request) {
        ReviewRes response = reviewService.createReview(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "후기 조회하기", description = """
            후기 조회하기 api 입니다.
            
            후기 id로 후기에 대한 내용을 볼 수 있습니다.
            
            후기 id, 후기 내용, 멘토 id, 멘티 id을 반환해줍니다.
            """)
    //후기 조회하기
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewRes> getReview(@PathVariable long reviewId) {
        ReviewRes response = reviewService.findReview(reviewId);
        return ResponseEntity.ok().body(response);
    }
}
