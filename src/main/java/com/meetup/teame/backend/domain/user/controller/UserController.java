package com.meetup.teame.backend.domain.user.controller;

import com.meetup.teame.backend.domain.user.dto.request.OnboardingReq;
import com.meetup.teame.backend.domain.user.dto.request.ReadCalenderReq;
import com.meetup.teame.backend.domain.user.dto.response.ReadCalenderRes;
import com.meetup.teame.backend.domain.user.dto.response.ReadMainRes;
import com.meetup.teame.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "user", description = "유저 관련 api")
public class UserController {
    private final UserService userService;

    @Operation(summary = "메인 페이지 조회", description = """
            현재 로그인한 유저의 메인 페이지를 조회합니다.
                        
            임시로 고정된 더미 유저의 데이터를 전달하는 식으로 구현되어 있습니다.
                        
            jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            """)
    @GetMapping("/main")
    public ResponseEntity<ReadMainRes> readMainPage() {
        return ResponseEntity
                .ok(userService.readMainPage());
    }

    @Operation(summary = "온보딩 정보 등록", description = """
            유저의 온보딩 정보를 등록합니다.
                        
            임시로 고정된 더미 유저의 온보딩 정보를 등록하는 식으로 구현되어 있습니다.
                        
            jwt토큰도 같이 전달해서 요청해주셔야 합니다.
                        
            현재 온보딩 정보로 입력 가능한 성격 유형 
            ("잔잔한", "활발한", "평화로운", "자연친화적인", "창의적인", "학문적인", "예술적인", "배울 수 있는")
            위 유형들 중 최소 1개 이상을 선택해서 Request Body에 담아 전송해주세요.
            """)
    @PatchMapping("/onboarding")
    public ResponseEntity<Void> setUserPersonality(@RequestBody @Valid OnboardingReq onboardingReq) {
        userService.setUserPersonality(onboardingReq);
        return ResponseEntity
                .ok().build();
    }


    @Operation(summary = "마이페이지 캘린더 조회", description = """
            유저의 캘린더 정보를 조회합니다.
                        
            임시로 고정된 더미 유저의 캘린더 정보를 조회하는 식으로 구현되어 있습니다.
                        
            jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            
            url 쿼리 파라미터로 다음과 같은 값을 전달해 주셔야 합니다.
            
            year : 년도
            
            month : 월
            """)
    @GetMapping("/mypage/calender")
    public ResponseEntity<ReadCalenderRes> readCalender(@ModelAttribute @Valid ReadCalenderReq readCalenderReq) {
        return ResponseEntity
                .ok(userService.readCalender(readCalenderReq));
    }
}
