package com.meetup.teame.backend.domain.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "recipable@gmail.com";
    private static int authNumber;

    public static void createNumber(){
        authNumber = (int)(Math.random() * (90000)) + 100000;
    }

    // 이메일 생성 메소드
    public MimeMessage CreateMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
//            String imageBase64 = encodeFileToBase64Binary("static/logo.jpg");

            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("Recipable 이메일 인증");

            // HTML 이메일 본문 생성
            String body = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "  body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
                    "  .email-container { background-color: #ffffff; padding: 20px; text-align: center; }" +
                    "  .header-image { width: 100%; height: auto; border: none; }" +
                    "  .email-content { color: #000000; }" +
                    "  h1 { font-size: 28px; font-weight: bold; color: #000000; }" + // h1 태그의 글씨 크기 및 볼드체 적용
                    "  p { font-size: 18px; font-weight: bold; color: #000000; }" + // p 태그의 글씨 크기 및 볼드체 적용
                    "  .auth-number { font-size: 24px; margin: 20px 0; color: #007bff; font-weight: bold; }" + // 인증 번호의 스타일을 유지합니다.
                    "  .thank-you { margin-top: 30px; color: #555; font-size: 16px; }" + // 감사의 인사 부분은 이전대로 유지
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class=\"email-container\">" +
//                    "<img class=\"header-image\" src=\"data:image/png;base64," + imageBase64 + "\" alt=\"Logo\" />" +
                    "<div class=\"email-content\">" +
                    "<h1>Welcome to DM!</h1>" +
                    "<p>😄안녕하세요, 저희 Recipable을 이용해 주셔서 진심으로 감사드립니다.😄</p>" +
                    "<p>👇아래 인증번호를 입력하시고 회원가입을 계속해주세요.👇</p>" +
                    "<p class=\"auth-number\">" + "인증번호 : " +  authNumber + "</p>" + // 생성된 인증번호 삽입
                    "<p class=\"thank-you\">감사합니다.</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";
            message.setText(body,"UTF-8", "html");

        } catch (MessagingException e) {
            e.printStackTrace(); // 예외 처리
        }
        return message;
    }

    public int sendMail(String mail){
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
        return authNumber;
    }
}