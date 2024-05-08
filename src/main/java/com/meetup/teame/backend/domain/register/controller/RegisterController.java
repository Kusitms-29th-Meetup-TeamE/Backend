package com.meetup.teame.backend.domain.register.controller;

import com.meetup.teame.backend.domain.auth.oauth.service.KakaoService;
import com.meetup.teame.backend.domain.email.dto.EmailRequest;
import com.meetup.teame.backend.domain.email.service.EmailService;
import com.meetup.teame.backend.domain.register.dto.RegisterRequest;
import com.meetup.teame.backend.domain.register.service.RegisterService;
import com.meetup.teame.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "register", description = "자체 로그인 관련 api")
public class RegisterController {

    private final RegisterService registerService;
    private final EmailService emailService;
    private final KakaoService kakaoService;
    private final UserService userService;

    @Operation(summary = "인증 번호 발송", description = """
            이메일 입력 하고 인증번호 발송하면 이메일로 인증번호 발송합니다.
            
            인증번호 맞게 입력했는지 확인은 프론트에서 진행합니다.
            """)
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        int authNumber = emailService.sendMail(request.getEmail());
        String number = "" + authNumber;
        return ResponseEntity.ok().body(number);
    }

    //이름, 비밀번호, 생년월일, 거주지 입력
    @Operation(summary = "사용자 정보 입력", description = """
            이메일 인증을 마치면 사용자 정보를 입력합니다.
            
            사용자 정보 입력을 마치고 회원가입에 성공하면 "회원가입 성공"이라는 메세지를 반환합니다.
            """)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        System.out.println("메서드 시작");
        Long userId = registerService.register(request);
        System.out.println("저장 메서드까지는 함");
        HttpHeaders headers = kakaoService.getLoginHeader(userService.findById(userId));
        return ResponseEntity.ok().headers(headers).body("회원가입 성공");
    }
}
