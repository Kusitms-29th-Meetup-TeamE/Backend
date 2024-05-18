package com.meetup.teame.backend.domain.user.controller;

import com.meetup.teame.backend.domain.activity.dto.response.ActivitySummaryRes;
import com.meetup.teame.backend.domain.review.dto.response.MyReviewRes;
import com.meetup.teame.backend.domain.user.dto.request.MyExperienceReq;
import com.meetup.teame.backend.domain.user.dto.request.OnboardingReq;
import com.meetup.teame.backend.domain.user.dto.request.ReadCalenderReq;
import com.meetup.teame.backend.domain.user.dto.request.UpdateUserReq;
import com.meetup.teame.backend.domain.user.dto.response.*;
import com.meetup.teame.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "사용자 기본 정보 조회", description = """
                        
            사용자의 기본 정보를 볼 수 있는 api입니다.
                        
            추후 로그인 적용 시에는 jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            """)
    //기본 정보 조회
    @GetMapping("/info")
    public ResponseEntity<UserInfoRes> getUserInfo() {
        UserInfoRes userInfo = userService.getUserInfo();
        return ResponseEntity.ok().body(userInfo);
    }

    @Operation(summary = "사용자 기본 정보 수정", description = """
            사용자의 기본 정보를 수정하는 api입니다.
                        
            name, email, imageUrl, location 데이터를 받습니다.
            """)
    //기본 정보 수정
    @PutMapping("/info")
    public ResponseEntity<UserInfoRes> updateUserInfo(@RequestBody UpdateUserReq request) {
        UserInfoRes userInfo = userService.updateUserInfo(request);
        return ResponseEntity.ok().body(userInfo);
    }

    @Operation(summary = "내 후기 목록 조회", description = """
            내 후기 목록을 조회하는 api입니다.

            경험 활동 유형을 param으로 보낼 수 있습니다.

            변수명은 type입니다.
            """)
    //내 후기 목록 조회
    @GetMapping("/reviews")
    public ResponseEntity<List<MyReviewRes>> getMyReviews(@RequestParam(required = false) String type) {
        List<MyReviewRes> myReviews = userService.getMyReviews(type);
        return ResponseEntity.ok().body(myReviews);
    }

    @Operation(summary = "내 활동 참여 목록 조회", description = """
            내 활동 참여 목록을 조회하는 api입니다.
                        
            api 요청 시 보낼 데이터는 token 이외에 없습니다.
            """)
    @GetMapping("/activities")
    public ResponseEntity<List<ActivitySummaryRes>> getMyActivities() {
        List<ActivitySummaryRes> myActivities = userService.getMyActivities();
        return ResponseEntity.ok().body(myActivities);
    }

    @Operation(summary = "배움 프로필 생성", description = """
            배움 프로필 생성 api입니다.
            
            배움 제목(title), 배움 유형(experienceType), detail, 나의 한마디(introduce)를 입력헤주세요.
            """)
    @PostMapping("/experience-profile")
    public ResponseEntity<String> createExperienceProfile(@RequestBody MyExperienceReq request) {
        String response = userService.createExperienceProfile(request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(summary = "배움 프로필 조회", description = """
            배움 프로필 조회 api입니다.
            
            """)
    @GetMapping("/experience-profile")
    public ResponseEntity<ReadExperienceProfileRes> getExperienceProfile() {
        ReadExperienceProfileRes response = userService.getExperienceProfile();
        return ResponseEntity.ok().body(response);
    }
}
