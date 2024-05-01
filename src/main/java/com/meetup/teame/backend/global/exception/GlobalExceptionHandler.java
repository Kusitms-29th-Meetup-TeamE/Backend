package com.meetup.teame.backend.global.exception;

import com.meetup.teame.backend.global.exception.dto.ExceptionRes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionRes> handleCustomException(CustomException exception) {
        return ResponseEntity
                .status(exception.getContent().getHttpStatus())
                .body(ExceptionRes.of(exception.getContent().getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionRes> handleInputFieldException(MethodArgumentNotValidException e) {
        FieldError mainError = e.getFieldErrors().get(0);
        String[] errorInfo = Objects.requireNonNull(mainError.getDefaultMessage()).split(":");
        String message = errorInfo[0];
        return ResponseEntity
                .badRequest()
                .body(new ExceptionRes(message));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionRes> handleJsonException(HttpMessageNotReadableException e) {
        log.warn("Json Exception ErrMessage={}\n", e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ExceptionRes("Json 형식이 올바르지 않습니다."));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionRes> handleRequestMethodException(HttpRequestMethodNotSupportedException e) {
        log.warn("Http Method not supported Exception ErrMessage={}\n", e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ExceptionRes("해당 요청에 대한 API가 존재하지 않습니다. 엔드 포인트를 확인해주시길 바랍니다. "));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionRes> unhandledException(Exception e, HttpServletRequest request) {
        log.error("UnhandledException: {} {} errMessage={}\n",
                request.getMethod(),
                request.getRequestURI(),
                e.getMessage()
        );
        return ResponseEntity
                .internalServerError()
                .body(new ExceptionRes("예상하지 못한 오류가 발생했습니다. 백엔드 팀에 문의바랍니다."));
    }
}


