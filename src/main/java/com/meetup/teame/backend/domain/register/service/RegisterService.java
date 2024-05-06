package com.meetup.teame.backend.domain.register.service;

import com.meetup.teame.backend.domain.register.dto.RegisterRequest;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    /**
     * 회원가입
     * client에게 RegisterRequest(name,email,password,location)를 입력 받아 비밀번호 암호화 후 저장
     * email, userName 중복체크는 안 할 예정
     */
    public void register(RegisterRequest registerRequest) {
        String encodedPassword = encoder.encode(registerRequest.getPassword());
        userRepository.save(registerRequest.toEntity(registerRequest,encodedPassword));
    }

}
