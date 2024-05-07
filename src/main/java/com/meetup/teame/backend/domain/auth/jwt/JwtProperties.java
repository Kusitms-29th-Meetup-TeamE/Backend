package com.meetup.teame.backend.domain.auth.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    //private String issuer;
    @Value("${jwt.secret}")
    private String secretKey;
}
