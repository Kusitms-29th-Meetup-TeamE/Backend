package com.meetup.teame.backend.domain.auth.oauth.dto;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getName() {
        // kakao_account라는 Map에서 추출
        return (String) ((Map) attribute.get("properties")).get("nickname");
    }

    @Override
    public String getGender() {
        return (String) ((Map) attribute.get("kakao_account")).get("gender");
    }

    @Override
    public String getBirthyear() {
        return (String) ((Map) attribute.get("kakao_account")).get("birthyear");
    }
}
