package com.meetup.teame.backend.domain.auth.oauth.dto;
import com.meetup.teame.backend.domain.user.dto.oauth.KakaoUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final KakaoUserDto kakaoUserDto;

    //원래는 이 메서드로 받은 데이터 값을 리턴할 수 있지만 구글,네이버,카카오 등에 따라 attribute가 달라서 안쓸거
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return kakaoUserDto.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return kakaoUserDto.getName();
    }

    public Long getId() {
        return kakaoUserDto.getId();
    }

}
