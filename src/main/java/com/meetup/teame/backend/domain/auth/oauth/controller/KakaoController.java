package com.meetup.teame.backend.domain.auth.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meetup.teame.backend.domain.auth.oauth.dto.CreateOauthUserRequest;
import com.meetup.teame.backend.domain.auth.oauth.dto.CreateUserRequest;
import com.meetup.teame.backend.domain.auth.oauth.service.KakaoService;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class KakaoController {

    private final UserService userService;
    private final KakaoService kakaoService;

    @GetMapping("/login/kakao")
    public ResponseEntity<Object> kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        String kakaoAccessToken = kakaoService.getKakaoAccessToken(code); //인가코드로 카카오 엑세스 토큰 받아오기
        CreateOauthUserRequest request = kakaoService.getKakaoInfo(kakaoAccessToken); //엑세스 토큰으로 카카오 사용자 정보 받아오기
        boolean checkExist = kakaoService.userExists(request.getEmail());
        if(checkExist) { //이미 가입된 회원
            /*Optional<User> userOptional*/User user = userService.findByEmail(request.getEmail());
            //User user = userOptional.get();
            HttpHeaders headers = kakaoService.getLoginHeader(user);

            return ResponseEntity.ok().headers(headers).body("login");
            //로그인 처리하기
        } else { //신규 회원
            return ResponseEntity.ok(request);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signup(@RequestBody CreateUserRequest request) { //이미 있는 회원인지 확인해야됨
        User user = userService.createUser(request);
        Long userId = userService.save(user);
        HttpHeaders headers = kakaoService.getLoginHeader(userService.findById(userId));
        return ResponseEntity.ok().headers(headers).body("OK");
    }
}