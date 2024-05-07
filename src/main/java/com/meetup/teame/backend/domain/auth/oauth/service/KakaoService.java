package com.meetup.teame.backend.domain.auth.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetup.teame.backend.domain.auth.jwt.JwtProvider;
import com.meetup.teame.backend.domain.auth.oauth.dto.CreateOauthUserRequest;
import com.meetup.teame.backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class KakaoService {

    @Value("${kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    private final JwtProvider jwtProvider;

    //카카오 엑세스 토큰으로 사용자 정보 받아오기
    public CreateOauthUserRequest getKakaoInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(KAKAO_INFO_URL, HttpMethod.GET, entity, String.class);
        CreateOauthUserRequest userRequest = new CreateOauthUserRequest();

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // 필수 정보
            String nickname = jsonNode.path("properties").path("profile_nickname").asText();
            String name = jsonNode.path("properties").path("name").asText();
            String gender = jsonNode.path("kakao_account").path("gender").asText();
            String birthyear = jsonNode.path("kakao_account").path("birthyear").asText();

            // 선택 정보
            String profileImage = jsonNode.path("properties").path("profile_image").asText("");
            String email = jsonNode.path("kakao_account").path("account_email").asText("");

            System.out.println("= = = " + email + " " + gender + " " + nickname + " " + name + " " + birthyear);
            userRequest.setEmail(email);
            userRequest.setGender(gender);
            userRequest.setNickname(nickname);
            userRequest.setName(name);
            userRequest.setBirthYear(birthyear);
            userRequest.setProfileImage(profileImage);

            return userRequest;
        }
        return null;
    }

    public String getKakaoAccessToken(String code) throws JsonProcessingException { //인가코드로 카카오 엑세스 토큰 받아오기
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", "authorization_code");
        data.add("client_id", KAKAO_CLIENT_ID);
        data.add("client_secret", KAKAO_CLIENT_SECRET);
        data.add("code", code); // 카카오로부터 받은 인가 코드
        data.add("redirect_uri", KAKAO_REDIRECT_URI); // 카카오로부터 등록한 리다이렉트 URI

        // 요청 객체 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(data, headers);

        // RestTemplate를 이용하여 POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String accessToken = jsonNode.get("access_token").asText();

            return accessToken;
        } else {
            return null; //나중에 제대로
        }
    }

    public HttpHeaders getLoginHeader(User user) {
        //액세스 토큰 생성 -> 패스에 액세스 토큰을 추가
        String accessToken = jwtProvider.generateToken(user, ACCESS_TOKEN_DURATION);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }
}
