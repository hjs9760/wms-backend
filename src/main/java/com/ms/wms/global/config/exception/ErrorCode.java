package com.ms.wms.global.config.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE( "C001", "Binding Error"),
    INTERNAL_SERVER_ERROR( "C002"),
    NOT_FOUND_ROW("C003"),
    DUPLICATE_ROW( "C004"),

    // Member
    MEMBER_ACCESS_DENIED( "C100");

    private String code;
    private String message;

    // default exception
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // custom exception
    ErrorCode(String code) {
        this.code = code;
    }
}
