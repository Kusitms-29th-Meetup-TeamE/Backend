package com.meetup.teame.backend.domain.register.controller;

import com.meetup.teame.backend.domain.email.dto.EmailRequest;
import com.meetup.teame.backend.domain.email.service.MailService;
import com.meetup.teame.backend.domain.register.dto.RegisterRequest;
import com.meetup.teame.backend.domain.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterController {

    private final RegisterService registerService;
    private final MailService mailService;

    //이메일 입력 받고 인증번호 발송
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        int authNumber = mailService.sendMail(request.getEmail());
        String number = "" + authNumber;
        return ResponseEntity.ok().body(number);
    }

    //이름, 비밀번호, 생년월일, 거주지 입력
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        registerService.register(registerRequest);
        return ResponseEntity.ok().body("회원가입을 성공하였습니다.");
    }

}
