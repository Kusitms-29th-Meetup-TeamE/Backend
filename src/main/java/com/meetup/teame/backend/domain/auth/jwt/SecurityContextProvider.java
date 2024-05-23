package com.meetup.teame.backend.domain.auth.jwt;

import com.meetup.teame.backend.global.exception.CustomException;
import com.meetup.teame.backend.global.exception.ExceptionContent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.util.Objects;

public class SecurityContextProvider {
    public static Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (Objects.isNull(principal)) {
            throw new CustomException(ExceptionContent.NOT_AUTHENTICATION);
        }
        return (Long) principal;
    }

    public static boolean isAnonymousUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ANONYMOUS"));
    }

    public static void setupSecurityContextForTest(Long userId) {
        UserAuthentication authentication = new UserAuthentication(userId, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
