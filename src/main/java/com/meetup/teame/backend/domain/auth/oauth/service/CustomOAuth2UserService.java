package com.meetup.teame.backend.domain.auth.oauth.service;

import com.meetup.teame.backend.domain.auth.oauth.dto.CustomOAuth2User;
import com.meetup.teame.backend.domain.auth.oauth.dto.KakaoResponse;
import com.meetup.teame.backend.domain.auth.oauth.dto.OAuth2Response;
import com.meetup.teame.backend.domain.user.dto.oauth.KakaoUserDto;
import com.meetup.teame.backend.domain.user.entity.Gender;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //OAuth2LoginAuthenticationProvider로부터 받은 사용자 정보를 얻기위한 메서드
        OAuth2User oAuth2User = super.loadUser(userRequest);
        //여기에 사용자 정보 담겨있음
        System.out.println(oAuth2User);

        //카카오인지 네이버인지 어디에서 온 요청인지 알기 위한 id 획득
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String kakaoId = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        User existData = userRepository.findByKakaoId(kakaoId);

        String genderStr = oAuth2Response.getGender();
        Gender gender = null;
        if (genderStr != null) {
            if (genderStr.equalsIgnoreCase("male")) {
                gender = Gender.MALE;
            } else if (genderStr.equalsIgnoreCase("female")) {
                gender = Gender.FEMALE;
            }
        }

        if (existData == null) {
            User user = User.ofKakao(kakaoId, oAuth2Response.getName(), gender, oAuth2Response.getBirthyear(), "ROLE_USER");
            userRepository.save(user);
            KakaoUserDto kakaoUserDto = new KakaoUserDto(user.getId(), kakaoId, oAuth2Response.getName(), oAuth2Response.getGender(), oAuth2Response.getBirthyear());

            return new CustomOAuth2User(kakaoUserDto);
        }
        else {
            existData.setName(oAuth2Response.getName());
            userRepository.save(existData);
            KakaoUserDto kakaoUserDto = new KakaoUserDto(existData.getId(), kakaoId, oAuth2Response.getName(), oAuth2Response.getGender(), oAuth2Response.getBirthyear());

            return new CustomOAuth2User(kakaoUserDto);
        }
    }

}
