package com.ms.wms.global.config.exception;

import com.ms.wms.global.config.exception.business.DuplicateException;
import com.ms.wms.global.config.exception.business.NoExistException;
import com.ms.wms.global.config.exception.business.UnAuthorityException;
import com.ms.wms.global.config.slack.SlackChannel;
import com.ms.wms.global.config.slack.SlackMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final SlackMessage slackMessage;

    /**
     *  @Valid or @Validated 으로 binding error 발생시 발생
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  ex.) @RequestBody
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 운동, 루틴 조회시 조회결과가 없을 경우
     */
    @ExceptionHandler(NoExistException.class)
    protected ResponseEntity<ErrorResponse> handleNoExistException(NoExistException e) {
        log.error("NoExistException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_ROW, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * 운동, 루틴 등록시 중복된 운동명, 루틴명이 있을 경우
     */
    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateException e) {
        log.error("DuplicateException", e);
        slackMessage.sendSlackMessage(SlackChannel.CHANNEL_WMS, e.getMessage());
        final ErrorResponse response = ErrorResponse.of(ErrorCode.Duplicate_ROW, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * 잡근 권한이 없는 유저가 접근시
     */
    @ExceptionHandler(UnAuthorityException.class)
    protected ResponseEntity<ErrorResponse> handleUnAuthorityException(UnAuthorityException e) {
        log.error("UnAuthorityException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.MEMBER_ACCESS_DENIED, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    /**
     * 비즈니스 요규사항이 아닌 예외
     * 스프링 및 라이브러리 등 자체적으로 발생하는 예외
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception!", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
