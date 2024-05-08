package com.meetup.teame.backend.domain.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String SENDER_EMAIL_ADDRESS;

    private final JavaMailSender javaMailSender;
    private static int authNumber;

    public static void createNumber(){
        authNumber = (int)(Math.random() * (90000)) + 100000;
    }

    // 파일에서 HTML 이메일 템플릿을 읽는 메소드
    private String readEmailTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource("/templates/email.html");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    // 이메일 생성 메소드
    public MimeMessage CreateMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        System.out.println("Sending email to: " + mail); // 로그 추가

        try {

            String htmlContent = readEmailTemplate();
            htmlContent = htmlContent.replace("{authNumber}", Integer.toString(authNumber)); // 템플릿 내의 플레이스홀더를 인증번호로 교체


            message.setFrom(SENDER_EMAIL_ADDRESS);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("또바 이메일 인증");
            message.setText(htmlContent, "UTF-8", "html");

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public int sendMail(String mail){
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
        return authNumber;
    }
}