package com.meetup.teame.backend.domain.activity.controller;

import com.meetup.teame.backend.domain.activity.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "활동 좋아요 활성화", description = """
            활동 좋아요를 활성화 하는 api입니다.
            
            활동 id를 받아서 활동 좋아요를 추가합니다.
            """)
    //활동 좋아요 활성화
    @PostMapping("/activate-like/{activityId}")
    public ResponseEntity<Void> activateLike(@PathVariable long activityId) {
        likeService.activateLike(activityId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "활동 좋아요 비활성화", description = """
            활동 좋아요를 비활성화 하는 api입니다.
            
            활동 id를 받아서 활동 좋아요를 삭제합니다.
            """)
    //활동 좋아요 비활성화
    @PostMapping("/deactivate-like/{activityId}")
    public ResponseEntity<Void> deactivateLike(@PathVariable long activityId) {
        likeService.deactivateLike(activityId);
        return ResponseEntity.ok().build();
    }
}
