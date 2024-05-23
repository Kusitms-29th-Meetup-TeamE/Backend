package com.meetup.teame.backend.domain.auth.service;

import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class LoginService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public boolean userExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUserValid(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return encoder.matches(password, user.getPassword());
    }

}
