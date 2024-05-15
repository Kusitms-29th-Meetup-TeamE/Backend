package com.meetup.teame.backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ExceptionContent {
    INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    LOGIN_FAILURE(UNAUTHORIZED, "로그인에 실패했습니다."),
    NOT_AUTHENTICATION(UNAUTHORIZED, "인증이 필요합니다."),

    NOT_AUTHORIZATION(FORBIDDEN, "권한이 필요합니다."),

    BAD_REQUEST_SIGNUP(BAD_REQUEST, "회원가입에 실패했습니다. 이미 가입한 이메일입니다."),
    BAD_REQUEST_PERSONALITY(BAD_REQUEST, "잘못된 요청입니다. 유효하지 않은 성격입니다."),
    BAD_REQUEST_EXPERIENCE_TYPE(BAD_REQUEST, "잘못된 요청입니다. 유효하지 않은 경험 유형입니다."),
    BAD_REQUEST_SORT_TYPE(BAD_REQUEST, "잘못된 요청입니다. 유효하지 않은 정렬 방식입니다."),
    BAD_REQUEST_ALREADY_JOIN_CHATROOM(BAD_REQUEST, "이미 참여한 채팅방입니다."),

    NOT_FOUND_USER(NOT_FOUND, "존재하지 않는 사용자입니다."),
    NOT_FOUND_PERSONALITY(NOT_FOUND, "존재하지 않는 성격입니다."),
    NOT_FOUND_EXPERIENCE_TYPE(NOT_FOUND, "존재하지 않는 경험 유형입니다."),
    NOT_FOUND_ACTIVITY(NOT_FOUND, "존재하지 않는 활동입니다."),
    NOT_FOUND_AGENCY_TYPE(NOT_FOUND, "존재하지 않는 기관 유형입니다."),
    NOT_FOUND_EXPERIENCE(NOT_FOUND, "존재하지 않는 경험입니다."),
    NOT_FOUND_CHAT_ROOM(NOT_FOUND, "존재하지 않는 채팅방입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
