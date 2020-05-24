package com.ms.wms.global.config.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String code;
    private String message;
    private List<FieldError> errors;

        public static ErrorResponse of(ErrorCode errorCode, BindingResult result) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.message = errorCode.getMessage();
        errorResponse.code = errorCode.getCode();

        errorResponse.errors = result.getFieldErrors().stream()
                .map(fieldError -> new FieldError(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return errorResponse;
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.message = message;
        errorResponse.code = errorCode.getCode();

        return errorResponse;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.message = errorCode.getMessage();
        errorResponse.code = errorCode.getCode();

        return errorResponse;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private Object value;
        private String reason;
    }
}