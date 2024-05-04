package com.meetup.teame.backend.domain.user.controller;

import com.meetup.teame.backend.domain.user.dto.response.ReadMainRes;
import com.meetup.teame.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "user", description = "유저 관련 api")
public class UserController {
    private final UserService userService;

    @Operation(summary = "메인 페이지 조회", description = """
            현재 로그인한 유저의 메인 페이지를 조회합니다.
            
            아직 로그인이 없어서 임시로 고정된 더미 유저의 데이터를 전달하는 식으로 구현되어 있습니다.
            
            추후 로그인 적용 시에는 jwt토큰도 같이 전달해서 요청해주셔야 합니다.
            """)
    @GetMapping("/main")
    public ResponseEntity<ReadMainRes> readMainPage() {
        return ResponseEntity
                .ok(userService.readMainPage());
    }
}
