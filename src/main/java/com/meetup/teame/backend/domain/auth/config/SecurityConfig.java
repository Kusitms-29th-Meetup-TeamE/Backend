package com.meetup.teame.backend.domain.auth.config;

import com.meetup.teame.backend.domain.auth.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(c -> c
                        .requestMatchers(
                                "/sign-up",
                                "/login/kakao",
                                "/login/local",
                                "/send-email",
                                "/register",
                                "/users/main",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/healths",
                                "/ws/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, ExceptionTranslationFilter.class)
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:3000", "https://api.yeongjin.site", "https://ddoba.vercel.app"));
                    config.setAllowedMethods(List.of("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(List.of("*"));
                    config.setMaxAge(3600L);
                    config.addExposedHeader("Authorization");
                    return config;
                }))
                .headers(c -> c.frameOptions(c2 -> c2.disable()));
        return http.build();
    }
}