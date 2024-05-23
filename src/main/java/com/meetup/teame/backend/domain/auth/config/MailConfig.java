package com.meetup.teame.backend.domain.auth.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String MAIL_HOST;

    @Value("${spring.mail.username}")
    private String MAIL_ADDRESS;

    @Value("${spring.mail.password}")
    private String MAIL_PASSWORD;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        // SMTP 서버 주소를 'smtp.gmail.com'으로 변경
        javaMailSender.setHost(MAIL_HOST);
        javaMailSender.setUsername(MAIL_ADDRESS); // 사용자 이메일 주소
        javaMailSender.setPassword(MAIL_PASSWORD); // 앱 비밀번호 또는 사용자 비밀번호

        // TLS를 사용하는 587 포트 설정
        javaMailSender.setPort(587);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true"); // TLS 활성화
        properties.setProperty("mail.debug", "true");

        // 'smtp.gmail.com'으로 변경
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        return properties;
    }
}
