package com.meetup.teame.backend.domain.auth.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meetup.teame.backend.domain.auth.oauth.dto.CreateOauthUserRequest;
import com.meetup.teame.backend.domain.auth.oauth.dto.CreateUserRequest;
import com.meetup.teame.backend.domain.auth.oauth.service.KakaoService;
import com.meetup.teame.backend.domain.auth.service.LoginService;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import com.meetup.teame.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Tag(name = "oauth", description = "로그인 관련 api")
public class KakaoController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final KakaoService kakaoService;
    private final LoginService loginService;

    @Operation(summary = "카카오 로그인", description = """
            카카오 소셜 로그인을 진행합니다.
            
            이미 등록된 사용자면 "login"이 출력되고
            
            등록되지 않은 사용자면 "/sign-up"을 요청해서 거주지 정보를 추가로 받아줘야 합니다.
            """)
    @GetMapping("/login/kakao")
    public ResponseEntity<CreateOauthUserRequest> kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        String kakaoAccessToken = kakaoService.getKakaoAccessToken(code); //인가코드로 카카오 엑세스 토큰 받아오기
        CreateOauthUserRequest request = kakaoService.getKakaoInfo(kakaoAccessToken); //엑세스 토큰으로 카카오 사용자 정보 받아오기
        boolean checkExist = loginService.userExists(request.getEmail());
        if(checkExist) { //이미 가입된 회원
            User user = userRepository.findByEmail(request.getEmail());
            HttpHeaders headers = kakaoService.getLoginHeader(user);

            return ResponseEntity.ok().headers(headers).body(request);
            //로그인 처리하기
        } else { //신규 회원
            return ResponseEntity.ok().body(request);
        }
    }

    @Operation(summary = "사용자 등록", description = """
            사용자 정보 등록을 진행합니다.
            
            거주지까지 입력을 마친 정보로 DB에 사용자 정보를 등록합니다.
            
            Jwt 토큰을 헤더에 넣어서 "OK" 메세지와 함께 반환합니다.
            """)
    @PostMapping("/sign-up")
    public ResponseEntity<Object> signup(@RequestBody CreateUserRequest request) { //이미 있는 회원인지 확인해야됨
        User user = userService.createUser(request);
        Long userId = userService.save(user);
        HttpHeaders headers = kakaoService.getLoginHeader(userService.findById(userId));
        return ResponseEntity.ok().headers(headers).body("OK");
    }
}