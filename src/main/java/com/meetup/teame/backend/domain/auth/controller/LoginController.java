package com.meetup.teame.backend.domain.auth.controller;

import com.meetup.teame.backend.domain.auth.oauth.service.KakaoService;
import com.meetup.teame.backend.domain.auth.dto.request.LoginRequest;
import com.meetup.teame.backend.domain.auth.service.LoginService;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "local-login", description = "자체 로그인 관련 api")
public class LoginController {

    private final UserRepository userRepository;
    private final KakaoService kakaoService;
    private final LoginService loginService;

    @Operation(summary = "자체 로그인", description = """
            이메일 주소, 비밀번호를 입력하고 로그인 시도를 합니다.
            
            이메일이 맞는지 확인하고 틀리면 "등록되지 않은 이메일입니다." 메세지를 반환합니다.
            
            비밀번호가 맞는지 확인하고 틀리면 "잘못된 비밀번호입니다." 메세지를 반환합나다.
      
            로그인에 성공하면 "로그인 성공" 메세지를 반환합니다.
            """)
    @PostMapping("/login/local")
    public ResponseEntity<String> localLogin(@RequestBody LoginRequest request) {
        boolean existEmail = loginService.userExists(request.getEmail());
        boolean validPassword = loginService.checkUserValid(request.getEmail(), request.getPassword());
        if (existEmail) {
            if (validPassword) {
                User user = userRepository.findByEmail(request.getEmail());
                HttpHeaders headers = kakaoService.getLoginHeader(user);
                return ResponseEntity.ok().headers(headers).body("로그인 성공");
            }
            else {
                return ResponseEntity.ok().body("잘못된 비밀번호입니다.");
            }
        }
        else{
            return ResponseEntity.ok().body("등록되지 않은 이메일입니다.");
        }
    }
}
